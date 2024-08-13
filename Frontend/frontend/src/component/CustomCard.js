import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Toast } from 'react-bootstrap'; // Import Toast for messages
import './card.css'; // Create a separate CSS file for card styling
import Heading from '../component/Heading';
import CostInfo from './CostInfo';

function CustomCard({ imgSrc, title, page, id, price, isLoggedIn, cartdetailsid, onDelete, refresh, transtype }) {
  const navigate = useNavigate();
  const [message, setMessage] = useState(null); // State for message
  
  const customerId = localStorage.getItem('customerId'); // Assume customerId is stored in localStorage
  
  const handleLendProduct = async (id) => {
    try {
      const customerId = localStorage.getItem('customerId');
      
      // Execute the royalty calculation API (POST request)
      const royaltyResponse = await fetch(`http://localhost:8080/api/royaltyCalculations/calculate/${customerId}/${id}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });
  
      if (royaltyResponse.ok) {
        console.log('Royalty calculation successful');
        
        // Now proceed to add the product to the shelf
        const response = await fetch(`http://localhost:8080/api/my-shelves/addProduct/${customerId}/${id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
        });
  
        if (response.ok) {
          setMessage({ type: 'success', text: 'Product lent successfully!' });
          if (refresh) {
            refresh(); // Refresh the parent component if needed
          }
        } else {
          const errorData = await response.text();
          setMessage({ type: 'error', text: 'Failed to lend product. Please try again.' });
          console.error('Lend Error:', errorData);
        }
      } else {
        const royaltyErrorData = await royaltyResponse.text();
        setMessage({ type: 'error', text: 'Failed to calculate royalty. Please try again.' });
        console.error('Royalty Calculation Error:', royaltyErrorData);
      }
    } catch (error) {
      setMessage({ type: 'error', text: 'Error occurred while lending product.' });
      console.error('Request Error:', error);
    }
  };
    

  const checkIfProductInCart = async (customerId, productId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/cartDetails/check/${customerId}/${productId}`);
      if (response.ok) {
        const data = await response.json();
        return data.exists;
      } else {
        console.error('Failed to check product in cart');
        return false;
      }
    } catch (error) {
      console.error('Error checking product in cart:', error);
      return false;
    }
  };
const handleviewdetails=(id)=>{
navigate(`/productdetails/${id}`)
}
  const handleAddToCartClick = async () => {
    if (!isLoggedIn) {
      navigate('/login');
    } else {
      const customerId = localStorage.getItem('customerId');
      if (customerId) {
        const isProductInCart = await checkIfProductInCart(customerId, id);

        if (isProductInCart) {
          setMessage({ type: 'error', text: 'Product already in cart' });
          return; // Prevent adding duplicate product
        }

        const productPayload = JSON.stringify({
          product: {
            productId: id
          }
        });

        try {
          const response = await fetch(`http://localhost:8080/api/cartDetails/${customerId}`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: productPayload
          });

          if (response.ok) {
            console.log('Product added to cart successfully');
            setMessage({ type: 'success', text: 'Product added to cart successfully!' });
            const cartResponse = await fetch(`http://localhost:8080/api/customers/${localStorage.getItem('customerId')}`);
            const cartData = await cartResponse.json();
            console.log("cartData=",cartData)
           localStorage.setItem('noofbooks',cartData.cart.noofbooks)
           localStorage.setItem('cost',cartData.cart.cost)
            if (refresh) {
              refresh(cartData.cart.noofbooks,cartData.cart.cost); // Call the refresh function passed from the parent component
            }
          } else {
            console.error('Failed to add product to cart');
            const errorData = await response.text();
            console.error('Product Error details:', errorData);
            setMessage({ type: 'error', text: 'Failed to add product to cart. Please try again.' });
          }
        } catch (error) {
          console.error('Product Request Error:', error);
          setMessage({ type: 'error', text: 'Error occurred while adding product to cart.' });
        }
      } else {
        console.error('Customer ID not found');
        setMessage({ type: 'error', text: 'Customer ID not found. Please log in.' });
      }
    }
  };

  const handleDeleteClick = async () => {
    if (onDelete) {
      onDelete(); // Call the onDelete function passed from the parent component
    }
  };

  return (
    <div>
      <div className="card1">
        <div><img src={imgSrc} className="c1" alt="" /></div>
        <div className="info">
          <div className="heading"><Heading title={title} /></div>
          <div className="heading"><CostInfo title={price} /></div>
          {transtype === 'B' && <div className="text-success fw-bold mt-2">Purchased</div>}
          {transtype==='L'&&<div className='text-warning fw-bold mt-2'>Lent</div>}
          {page === "Category Page" && (
            <Button className="nav-link-custom" onClick={handleAddToCartClick}>
              Add to cart
            </Button>
          )}
          {page === "Cart Page" && (
            <Button 
              variant="danger" 
              style={{ marginTop: '10px' }}
              onClick={handleDeleteClick}
            >
              Delete
            </Button>
          )}
          {
            page==="Home Page"&&(
              <Button 
              variant="danger" 
              style={{ marginTop: '10px' }}
              onClick={()=>handleviewdetails(id)}
            >
              View details
            </Button>
            )
          }
          {
            page === "Lentpage" && (
              <Button 
  className="btn btn-warning"
  style={{ marginTop: '10px', backgroundColor: '#FFD700', borderColor: '#FFD700' }}
  onClick={() => handleLendProduct(id)}
>
  Lent
</Button>
            )
          }
        </div>
      </div>

      {/* Display message if available */}
      {message && (
        <div className="toast-container">
          <Toast bg={message.type === 'success' ? 'success' : 'danger'} onClose={() => setMessage(null)} delay={3000} autohide>
            <Toast.Body>{message.text}</Toast.Body>
          </Toast>
        </div>
      )}
    </div>
  );
}

export default CustomCard;
