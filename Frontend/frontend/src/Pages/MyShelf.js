import { useEffect, useState } from "react"
import HeadingPage from "../component/HeadingPage"
import CustomCard from "../component/CustomCard"
import { Container, Row, Col } from 'react-bootstrap';
function MyShelf(){
    const [shelfdteails,setShelfDetails]=useState([])
    useEffect(()=>{
        fetch(`http://localhost:8080/api/products-on-shelf/customer/${localStorage.getItem('customerId')}`).then(res=>res.json()).then(data=>setShelfDetails(data))
    },[])
    return(
        <div>

            <div style={{ marginLeft: "393px", marginTop: "3993px" }}>
                <HeadingPage title="My Shelf"></HeadingPage>
            </div>
            {console.log('shelfdetails=',shelfdteails)}
            <div className='cards'>
                {
                  !shelfdteails.error?
                <Container>
                  <Row>
                    {shelfdteails.map((product, index) => (
                      product.tranType=="B"?
                      <Col md={4} key={index}>
                        <CustomCard
                          title={product.product.productEnglishName}
                          content={product.product.productDescriptionShort} // Adjusted for proper content
                          imgSrc={product.product.productImage}
                          price={product.product.productSpCost}
                          transtype={product.tranType}
                       />
                      </Col>:""
                    ))}
                  </Row>
                </Container>:<h4>Shelf empty</h4>
}
              </div>
        </div>
    )
}
export default MyShelf