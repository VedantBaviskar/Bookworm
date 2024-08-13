import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function ProductDetails() {
  const { id } = useParams();
  const [product, setProduct] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/api/products/${id}`)
      .then(response => response.json())
      .then(data => setProduct(data))
      .catch(error => console.error('Error:', error));
  }, []);

  if (!product) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>{product.productEnglishName}</h1>
      <p>{product.productDescriptionLong}</p>
      <img src={product.productImage} alt={product.productEnglishName} style={{height:400, width:400}} />
      <p>Price: ${product.productSpCost}</p>
    </div>
  );
}

export default ProductDetails;