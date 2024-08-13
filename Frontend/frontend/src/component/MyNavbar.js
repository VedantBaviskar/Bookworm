import React, { useEffect, useState } from 'react';
import { Navbar, Nav, NavDropdown, Button } from 'react-bootstrap';
import { FaShoppingCart, FaUser } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import './navbar.css'; // Import a CSS file for additional styling if needed

function MyNavbar({ isLoggedIn, onLogout, cartItems, cartCost }) {
  const [producttype, setProductType] = useState([]);
  
  useEffect(() => {
    fetch('http://localhost:8080/api/product-types/getall')
      .then(res => res.json())
      .then(data => setProductType(data))
      .catch(error => console.log(error));
  }, []);
 

  return (
    <Navbar bg="dark" variant="dark" fixed='top' expand="lg">
      <Navbar.Brand as={Link} to="/">BookWorm</Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          <Nav.Link as={Link} to="/" className="nav-link-custom">Home</Nav.Link>
          <NavDropdown title="Categories" id="basic-nav-dropdown" className="nav-link-custom">
            {producttype.map(pt => (
               <NavDropdown.Item key={pt.id} as={Link} to={`/category/${pt.id}`}>
               {pt.name}
             </NavDropdown.Item>
            ))}
          </NavDropdown>
          <Nav.Link as={Link} to="/shelf" className="nav-link-custom">Shelf</Nav.Link>
          <Nav.Link as={Link} to="/cart" className="nav-link-custom">
            <FaShoppingCart /> <b>Cart</b>{localStorage.getItem('noofbooks') !== null 
    ? `(${localStorage.getItem('noofbooks')}) $${localStorage.getItem('cost')}` 
    : `(0) $0`}
          </Nav.Link>
          {!isLoggedIn?<Nav.Link as={Link} to="/signup" className="nav-link-custom">
            <FaUser /> Sign Up
          </Nav.Link>:""}
          <Nav.Link as={Link} to="/mylibrary" className="nav-link-custom">
            Library package
          </Nav.Link>
          <Nav.Link as={Link} to="/libraryuser" className="nav-link-custom">
            Lending Library
          </Nav.Link>
          <Nav.Link as={Link} to="/librarydetails" className="nav-link-custom">
            My Library
          </Nav.Link>
          {isLoggedIn ? (
            <Button variant="outline-light" onClick={onLogout}>Logout</Button>
          ) : (
            <Nav.Link as={Link} to="/login">Login</Nav.Link>
          )}
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
}

export default MyNavbar;
