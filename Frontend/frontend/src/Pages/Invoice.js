import React, { useEffect, useState } from 'react';
import './Invoice.css';
import { Button } from 'react-bootstrap';
import { Navigate, useNavigate } from "react-router-dom";

function Invoice() {
  const [invoiceDetails, setInvoiceDetails] = useState([]); // Initialize state to an empty array
  const [today, setToday] = useState('');
  const [total, setTotal] = useState(0);
  const navigate = useNavigate();
  useEffect(() => {
    fetch(`http://localhost:8080/api/invoice-details/customer/${localStorage.getItem('customerId')}`)
      .then(res => res.json())
      .then(data => {
        setInvoiceDetails(data);
        const totalCost = data.reduce((acc, invoice) => acc + invoice.product.productSpCost, 0);
        setTotal(totalCost);
      })
      .catch(error => console.error('Error fetching invoice details:', error));

    const currentDate = new Date();
    const formattedDate = formatDate([currentDate.getFullYear(), currentDate.getMonth() + 1, currentDate.getDate()]);
    setToday(formattedDate);
  }, []);

  function formatDate(dateArray) {
    if (Array.isArray(dateArray) && dateArray.length === 3) {
      const [year, month, day] = dateArray;
      return `${day}/${month}/${year}`;
    }
    return '';
  }

  function buynow(invoiceId) {
    const calculateRoyaltiesUrl = `http://localhost:8080/api/royaltyCalculations/calculate-royalties/${invoiceId}`;
    const deleteInvoiceDetailsUrl = `http://localhost:8080/api/invoice-details/invoice/${invoiceId}`;
    const deleteInvoiceUrl = `http://localhost:8080/api/invoices/${invoiceId}`;
    const cartId = localStorage.getItem('cartId'); // Replace this with the actual cart ID you want to delete
    const deleteCartDetailsUrl = `http://localhost:8080/api/cartDetails/cart/${cartId}`;
    const updateAndAddToShelfUrl = `http://localhost:8080/api/my-shelves/move/${localStorage.getItem('customerId')}`;

    fetch(updateAndAddToShelfUrl, { method: 'POST' })
      .then(response => {
        if (response.ok) {
          console.log('Shelf updated and products added successfully');
          return fetch(calculateRoyaltiesUrl, { method: 'POST' });
        } else {
          throw new Error('Failed to update shelf and add products');
        }
      })
      .then(response => {
        if (response.ok) {
          console.log('Royalties calculated successfully');
          return fetch(deleteInvoiceDetailsUrl, { method: 'DELETE' });
        } else {
          throw new Error('Failed to calculate royalties');
        }
      })
      .then(response => {
        if (response.ok) {
          console.log('Invoice details deleted successfully');
          return fetch(deleteInvoiceUrl, { method: 'DELETE' });
        } else {
          throw new Error('Failed to delete invoice details');
        }
      })
      .then(response => {
        if (response.ok) {
          console.log('Invoice deleted successfully');
        } else {
          throw new Error('Failed to delete invoice');
        }
      })
      .catch(error => console.error('Error during transaction:', error))
      .finally(() => {
        // Ensure the cart details are deleted even if an error occurs
        fetch(deleteCartDetailsUrl, { method: 'DELETE' })
          .then(response => {
            if (response.ok) {
              console.log('Cart details deleted successfully');
            } else {
              console.error('Failed to delete cart details');
            }
          })
          .catch(error => console.error('Error during deletion:', error));
      });
      localStorage.removeItem('noofbooks')
      localStorage.removeItem('cost')
      navigate('/') 
  }

  return (
    <div className='product-list'>
      {console.log(invoiceDetails)}
      <h2>INVOICE</h2>
      <p>Today's Date: {today}</p>
      <table border='1'>
        <thead>
          <tr>
            <th>Product Name</th>
            <th>ProductId</th>
            <th>Cost</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {invoiceDetails.map(invoice => (
            today === formatDate(invoice.invoice.invoiceDate) ?
              <tr key={invoice.invDtlId}>
                <td>{invoice.product.productEnglishName}</td>
                <td>{invoice.product.productId}</td>
                <td>{invoice.product.productSpCost}</td>
                <td>{formatDate(invoice.invoice.invoiceDate)}</td>
              </tr> : <tr><td colSpan="4">None</td></tr>
          ))}
        </tbody>
      </table>
      <br />
      Total: {total}
      <br />
      {invoiceDetails.length > 0 && (
        <Button onClick={() => buynow(invoiceDetails[0].invoice.invoiceId)}>Buy Now</Button>
      )}
    </div>
  );
}

export default Invoice;
