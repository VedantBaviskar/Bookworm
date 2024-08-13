import React, { useState, useEffect } from 'react';
import { Container, Form, Button, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom'; // Import useNavigate hook
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

const CustomerRegistration = () => {
  const [customer, setCustomer] = useState({
    customerName: '',
    customerEmail: '',
    customerPassword: '',
    phoneNumber: '',
    pan: '',
  });

  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [validationErrors, setValidationErrors] = useState({});
  const [registrationSuccess, setRegistrationSuccess] = useState(false); // Track registration success

  const navigate = useNavigate(); // Initialize useNavigate hook

  useEffect(() => {
    let interval;
    if (registrationSuccess) {
      interval = setInterval(() => {
        navigate('/login');
      }, 5000); // Navigate after 5 seconds
    }
    return () => clearInterval(interval); // Clear interval on cleanup
  }, [registrationSuccess, navigate]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCustomer((prevCustomer) => ({
      ...prevCustomer,
      [name]: value,
    }));
  };

  const validateForm = () => {
    const errors = {};

    if (!customer.customerName) errors.customerName = 'Customer Name is required';
    if (!customer.customerEmail) errors.customerEmail = 'Customer Email is required';
    else if (!/\S+@\S+\.\S+/.test(customer.customerEmail)) errors.customerEmail = 'Email is invalid';

    if (!customer.customerPassword) errors.customerPassword = 'Password is required';
    else if (customer.customerPassword.length < 6) errors.customerPassword = 'Password must be at least 6 characters';

    if (!customer.phoneNumber) errors.phoneNumber = 'Phone Number is required';
    else if (!/^\d{10}$/.test(customer.phoneNumber)) errors.phoneNumber = 'Phone Number must be 10 digits';

    if (!customer.pan) errors.pan = 'PAN is required';
    else if (!/^[A-Z]{5}[0-9]{4}[A-Z]{1}$/.test(customer.pan)) errors.pan = 'PAN must be in the format ABCDE1234F';

    setValidationErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) {
      return; // Stop form submission if there are validation errors
    }

    try {
      const response = await fetch('http://localhost:8080/api/customers', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(customer),
      });

      if (response.ok) {
        
        setError('');
        setCustomer({
          customerName: '',
          customerEmail: '',
          customerPassword: '',
          phoneNumber: '',
          pan: '',
        });

        // Set registration success to true
        setRegistrationSuccess(true);
      } else if (response.status === 409) {
        // If the customer already exists
        setMessage('');
        setError('Customer already exists.');
      } else {
        setMessage('');
        setError('Error: ' + response.statusText);
      }
    } catch (error) {
      setMessage('');
      setError('Error creating customer: ' + error.message);
    }
  };

  return (
    <Container className="mt-5">
      <h2>Customer Registration</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formCustomerName">
          <Form.Label>Customer Name</Form.Label>
          <Form.Control
            type="text"
            name="customerName"
            value={customer.customerName}
            onChange={handleChange}
            isInvalid={!!validationErrors.customerName}
          />
          <Form.Control.Feedback type="invalid">
            {validationErrors.customerName}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId="formCustomerEmail">
          <Form.Label>Customer Email</Form.Label>
          <Form.Control
            type="email"
            name="customerEmail"
            value={customer.customerEmail}
            onChange={handleChange}
            isInvalid={!!validationErrors.customerEmail}
          />
          <Form.Control.Feedback type="invalid">
            {validationErrors.customerEmail}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId="formCustomerPassword">
          <Form.Label>Customer Password</Form.Label>
          <Form.Control
            type="password"
            name="customerPassword"
            value={customer.customerPassword}
            onChange={handleChange}
            isInvalid={!!validationErrors.customerPassword}
          />
          <Form.Control.Feedback type="invalid">
            {validationErrors.customerPassword}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId="formPhoneNumber">
          <Form.Label>Phone Number</Form.Label>
          <Form.Control
            type="text"
            name="phoneNumber"
            value={customer.phoneNumber}
            onChange={handleChange}
            isInvalid={!!validationErrors.phoneNumber}
          />
          <Form.Control.Feedback type="invalid">
            {validationErrors.phoneNumber}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId="formPan">
          <Form.Label>PAN</Form.Label>
          <Form.Control
            type="text"
            name="pan"
            value={customer.pan}
            onChange={handleChange}
            isInvalid={!!validationErrors.pan}
          />
          <Form.Control.Feedback type="invalid">
            {validationErrors.pan}
          </Form.Control.Feedback>
        </Form.Group>

        <Button variant="primary" type="submit">
          Register
        </Button>
      </Form>

      {/* Conditionally render messages */}
      {message && <Alert variant="success" className="mt-3">{message}</Alert>}
      {error && <Alert variant="danger" className="mt-3">{error}</Alert>}

      {/* Conditionally render the login button if the customer already exists */}
      {registrationSuccess && (
        <Alert variant="info" className="mt-3">
          Registration successful! You will be redirected to the login page shortly.
        </Alert>
      )}
    </Container>
  );
};

export default CustomerRegistration;
