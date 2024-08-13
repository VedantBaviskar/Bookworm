import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import CustomCard from './component/CustomCard';
import HeadingPage from './component/HeadingPage';
import "./App.css";
import MyNavbar from './component/MyNavbar';
import HomePage from './Pages/HomePage';
import CategoryPage from './Pages/CategoryPage';
import LoginPage from './Pages/LoginPage';
import PrivateRoute from './component/PrivateRoute';
import CartPage from './Pages/CartPage';
import Invoice from './Pages/Invoice';
import MyShelf from './Pages/MyShelf';
import MyLibrary from './Pages/MyLibrary';
import UserLib from './Pages/UserLib';
import LibraryDetails from './Pages/LibraryDetails';
import CustomerRegistration from './Pages/CustomerRegistration';
import ProductDetails from './Pages/ProductDetails';
function App() {
  const [card, setCard] = useState([]);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [cartitems,setcartitems]=useState(0)
  const [cartItems, setCartItems] = useState(0);
  const [cartCost, setCartCost] = useState(0);

  const updateCartInfo = (items, cost) => {
    setCartItems(items);
    setCartCost(cost);
  };

  useEffect(() => {
    fetch('http://localhost:8080/api/products')
      .then(response => response.json())
      .then(data => setCard(data))
      .catch(error => console.error('Error:', error));
    
    // Check login status from localStorage or API on app load
    const loggedInStatus = localStorage.getItem('isLoggedIn') === 'true';
    setIsLoggedIn(loggedInStatus);
  }, []);
  // useEffect(()=>{
  //   fetch(`http://localhost:8080/api/customers/${localStorage.getItem('customerId')}`).then(res=>res.json()).then(data=>setcartitems(data.cart.noofbooks))
  // },[])
  const handleLogin = (success) => {
    if (success) {
      setIsLoggedIn(true);
      localStorage.setItem('isLoggedIn', 'true');
    }
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('cartId')
    localStorage.removeItem('customerId')
    localStorage.removeItem('noofbooks')
    localStorage.removeItem('cost')
    
    
    // Redirect to login page if needed
  };

  return (
    <div>
    <Router>
      <div>
        <MyNavbar isLoggedIn={isLoggedIn} onLogout={handleLogout} cartItems={cartItems} cartCost={cartCost} />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/category/:id" element={<CategoryPage isLoggedIn={isLoggedIn}  updateCartInfo={updateCartInfo} />} />
          <Route path="/login" element={<LoginPage onLogin={handleLogin} />} />
          <Route path="/cart" element={<PrivateRoute isLoggedIn={isLoggedIn} element={CartPage} cartitems={cartitems} />} />
          <Route path="/invoice" element={<Invoice></Invoice>}></Route>
         <Route path="/shelf" element={<PrivateRoute isLoggedIn={isLoggedIn} element={MyShelf} />} ></Route>
         <Route path="/mylibrary" element={<PrivateRoute isLoggedIn={isLoggedIn} element={MyLibrary} />} ></Route>
         <Route path="/libraryuser" element={<PrivateRoute isLoggedIn={isLoggedIn} element={UserLib} />} ></Route>
        <Route path="/librarydetails" element={<PrivateRoute isLoggedIn={isLoggedIn} element={LibraryDetails}/>}></Route>
        <Route path="/signup" element={<CustomerRegistration></CustomerRegistration>}></Route>
        <Route path="/productdetails/:id" element={<ProductDetails></ProductDetails>}></Route>
        </Routes>
      </div>
    </Router>
    </div>
  );
}

export default App;