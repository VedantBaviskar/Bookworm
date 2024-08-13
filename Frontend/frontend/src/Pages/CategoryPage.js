import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';
import CustomCard from '../component/CustomCard';
import HeadingPage from '../component/HeadingPage';
import Searchbox from '../component/Searchbox';
import DropdownComponent from '../component/DropDown';

function CategoryPage({ isLoggedIn }) {
  const { id } = useParams();
  const [products, setProducts] = useState([]);
  const [data, setData] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [refreshKey, setRefreshKey] = useState(0);

  useEffect(() => {
    fetchProducts();
  }, [id, refreshKey]);

  const fetchProducts = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/products/type/${id}`);
      const data = await response.json();
      setProducts(data);
      console.log('Products fetched:', data); // Confirm data update
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleGenreSelect = (data) => {
    setData(data); // Update products with the fetched data based on selected genre
  };

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value); // Update search query
  };

  const displayedProducts = (data.length > 0 ? data.map(item => item.product) : products)
    .filter(product =>
      product.productEnglishName.toLowerCase().includes(searchQuery.toLowerCase())
    );

  return (
    <div >
      <div style={{ marginLeft: "393px", marginTop: "1563px" }}>
        {products.length > 0 && products[0].productType && (
          <HeadingPage title={products[0].productType.name} />
        )}
      </div>
      <div style={{ marginLeft: "393px", marginTop: "193px", display: "flex", flexDirection: "row", gap: "10px" }}>
        <Searchbox placeholder="search" onchange={handleSearchChange} />
        <DropdownComponent index={id} onSelect={handleGenreSelect} />
      </div>
      <Container>
        <Row>
          {displayedProducts.map((product, index) => (
            <Col md={4} key={index}>
              <CustomCard
                title={product.productEnglishName}
                content={product.content}
                imgSrc={product.productImage}
                id={product.productId}
                price={product.productSpCost}
                page="Category Page"
                isLoggedIn={isLoggedIn}
                refresh={() => setRefreshKey(prevKey => prevKey + 1)} // Trigger refresh
              />
            </Col>
          ))}
        </Row>
      </Container>
    </div>
  );
}

export default CategoryPage;
