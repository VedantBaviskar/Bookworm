import { useEffect, useState } from "react"
import HeadingPage from "../component/HeadingPage"
import { Container, Row, Col } from 'react-bootstrap';
import CustomCard from "../component/CustomCard";
function UserLib({ isLoggedIn }){
    const [product,setproduct]=useState([])
    useEffect(()=>{
        fetch('http://localhost:8080/api/products')
        .then(response => response.json())
        .then(data => setproduct(data))
        .catch(error => console.error('Error:', error));
    },[])
    const [lib,setlib]=useState([])
    useEffect(()=>{
      fetch(`http://localhost:8080/api/customers/${localStorage.getItem('customerId')}`).then(res=>res.json()).then(res1=>setlib(res1.libraryPackage))
    },[])
    return (
        <div style={{marginTop:"693px"}}>
            <HeadingPage title="Lent a book"></HeadingPage>
            {console.log(product)}
            {console.log(lib)}
            <div>
            <div className='cards'>
                {
                  lib!=null?
                <Container>
                  <Row>
                    {product.map((product, index) => (
                      product.isLibrary?(
                      <Col md={4} key={index}>
                        <CustomCard
                          id={product.productId}
                          title={product.productEnglishName}
                          content={product.productDescriptionShort} // Adjusted for proper content
                          imgSrc={product.productImage}
                          price={product.productSpCost}
                          page="Lentpage"
                       />
                      </Col>):" "
                    ))}
                  </Row>
                </Container>:<h1>Please purchase a library package</h1>
}</div>
            </div>
            </div>
    )
}
export default UserLib