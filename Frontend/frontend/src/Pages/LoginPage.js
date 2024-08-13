import React, { useState } from 'react';
import { Form, Button, Alert } from 'react-bootstrap';
import './LoginPage.css';
import HeadingPage from '../component/HeadingPage';

const LoginPage = ({ onLogin }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/api/customers/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          customerEmail: email,
          customerPassword: password
        }),
      });

      if (response.ok) {
        const data = await response.json();
        setSuccess('Login successful!');
        setError(null);
        console.log(data)
        if(data.cart!=null){
          localStorage.setItem('cartId',data.cart.id)
          localStorage.setItem('noofbooks',data.cart.noofbooks)
          localStorage.setItem('cost',data.cart.cost)
        }
         console.log("cartid=",localStorage.getItem('cartId'))
        if(data.cart==null){
          try {
            const cartResponse = await fetch('http://localhost:8080/api/carts', {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify({
                cartMaster: {
                  noofbooks: 0,
                  cost: 0
                },
                customerId: data.customerId // Use the customerId from login response
              })
            });

            if (cartResponse.ok) {
              const cartData = await cartResponse.json();
              console.log('Cart created successfully', cartData);
              localStorage.setItem('cartId',cartData.id)
            } else {
              console.error('Failed to create cart');
              const errorData = await cartResponse.text();
              console.error('Error details:', errorData);
            }
          } catch (error) {
            console.error('Cart creation error:', error);
          }
        }
        localStorage.setItem('customerId',data.customerId)
        onLogin(true, data.customerId); // Pass the customerId to the parent component
      } else {
        setSuccess(null);
        setError('Invalid email or password');
      }
    } catch (err) {
      setSuccess(null);
      setError('An error occurred during login');
      console.log(err)
    }
  };

  return (
    <div className="login-page">
      <div style={{ marginLeft: "393px", marginTop: "33px" }}>
        <HeadingPage title="Login Page" />
      </div>
      <div className="login-component">
        <Form onSubmit={handleLogin} className='formcontrol'>
          <Form.Group controlId="formEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              style={{ width: "500px" }}
            />
          </Form.Group>
          <br />
          <Form.Group controlId="formPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Form.Group>
          <br />
          <Button variant="primary" type="submit">
            Login
          </Button>
        </Form>
      </div>
      {error && <Alert variant="danger" className="mt-3">{error}</Alert>}
      {success && <Alert variant="success" className="mt-3">{success}</Alert>}
    </div>
  );
};

export default LoginPage;
