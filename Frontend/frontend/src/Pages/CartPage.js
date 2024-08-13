import { useState, useEffect } from "react";
import HeadingPage from '../component/HeadingPage';
import { Container, Row, Col, Alert,Button } from 'react-bootstrap';
import CustomCard from '../component/CustomCard';
import { Navigate, useNavigate } from "react-router-dom";

function CartPage() {
    const [cartDetails, setCartDetails] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);
    const navigate = useNavigate(); 
    useEffect(() => {
        fetchCartDetails();
    }, []);

    const fetchCartDetails = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/cartDetails/customer/${localStorage.getItem('customerId')}`);
            const data = await response.json();
            setCartDetails(data);
            const total = data.reduce((acc, item) => acc + item.product.productSpCost, 0);
            setTotalPrice(total);
        } catch (error) {
            console.error('Error:', error);
        }
    };
    
    const handleDelete = async (cartdetailsId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/cartDetails/${cartdetailsId}`, {
                method: 'DELETE',
            });
            if (response.ok) {
                // let noOfBooks = parseInt(localStorage.getItem('noofbooks')) || 0; // Get the current value and convert to an integer
                // noOfBooks -= 1; // Su
                // localStorage.setItem('noofbooks', noOfBooks);
                const cartResponse = await fetch(`http://localhost:8080/api/customers/${localStorage.getItem('customerId')}`);
            const cartData = await cartResponse.json();
            localStorage.setItem('cost',cartData.cart.cost) 
            localStorage.setItem('noofbooks',cartData.cart.noofbooks)
                fetchCartDetails(); // Refresh cart details after deletion
                console.log('Product removed from cart successfully');
            } else {
                console.error('Failed to remove product from cart');
                const errorData = await response.text();
                console.error('Delete Error details:', errorData);
            }
        } catch (error) {
            console.error('Delete Request Error:', error);
        }
    };
    const handleCheckout = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/invoices/${localStorage.getItem('customerId')}`, {
                method: 'POST'
            });
            if (response.ok) {
                const invoiceData = await response.json();
                console.log('Invoice created successfully:', invoiceData);
                 navigate('/invoice') // Navigate to the invoice page
            } else {
                console.error('Failed to create invoice');
                const errorData = await response.text();
                console.error('Invoice Creation Error details:', errorData);
            }
        } catch (error) {
            console.error('Checkout Request Error:', error);
        }
    };
    return (
        <div>
            <div style={{ marginLeft: "193px", marginTop: "133px" }}>
                <HeadingPage title="Cart Details" />
            </div>
            <div >
                <Container >
                    {console.log(cartDetails)}
                    <Row>
                        {cartDetails.map((product, index) => (
                            <Col md={6} key={index}>
                                <CustomCard
                                    title={product.product.productEnglishName}
                                    content={product.product.productDescriptionShort}
                                    imgSrc={product.product.productImage}
                                    price={product.product.productSpCost}
                                    cartdetailsid={product.id} // Pass cartdetailsid to CustomCard
                                    onDelete={() => handleDelete(product.ctid)} // Pass handleDelete function
                                    page="Cart Page"
                                />
                            </Col>
                        ))}
                    </Row>
                </Container>
            </div>
            <div style={{ marginTop: "20px", marginLeft: "93px" }}>
                <Alert variant="info">
                    <h4>Total Price: {totalPrice}</h4>
                </Alert>
                <Button 
                    variant="primary" 
                    onClick={handleCheckout}    
                >
                    Checkout
                </Button>
            </div>
        </div>
    );
}

export default CartPage;
