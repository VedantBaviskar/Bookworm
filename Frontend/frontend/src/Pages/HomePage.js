import HeadingPage from "../component/HeadingPage"
import CustomCard from "../component/CustomCard"
import { Container, Row, Col } from 'react-bootstrap';
import { useState,useEffect } from "react";
import "./HomePage.css"
function HomePage(props){
    const [card, setCard] = useState([]);

    useEffect(() => {
      fetch('http://localhost:8080/api/products')
        .then(response => response.json())
        .then(data => setCard(data))
        .catch(error => console.error('Error:', error));
    }, []);
    return(

    <div>
              <div style={{ marginLeft: "493px", marginTop: "1333px" }}>
                <HeadingPage title="Home Page" />
              </div>
              <div className='cards'>
                <Container>
                  <Row>
                    {card.map((product, index) => (
                      <Col md={4} key={index}>
                        <CustomCard
                          id={product.productId}
                          title={product.productEnglishName}
                          content={product.productDescriptionShort} // Adjusted for proper content
                          imgSrc={product.productImage}
                          price={product.productSpCost}
                          page="Home Page"
                       />
                      </Col>
                    ))}
                  </Row>
                </Container>
              </div>
            </div>
)
}
export default HomePage