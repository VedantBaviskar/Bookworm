import React, { useEffect, useState } from 'react';
import { Dropdown } from 'react-bootstrap';

const DropdownComponent = ({ index, onSelect }) => {
  const [options, setOptions] = useState([]);
  const [selectedOption, setSelectedOption] = useState('Select an option');

  useEffect(() => {
    fetch('http://localhost:8080/api/genres/getall')
      .then((res) => res.json())
      .then((data) => setOptions(data));
  }, []);

  const handleSelect = (eventKey) => {
    const selected = options.find((option) => option.id === parseInt(eventKey));
    if (selected) {
      setSelectedOption(selected.name);
      console.log(`Selected: ${selected.name}`);
      fetch(`http://localhost:8080/api/product-genres/products/type/${index}/genre/${selected.id}`)
        .then((res) => res.json())
        .then((data) => onSelect(data)); // Pass the fetched data back to the parent component
    }
  };

  return (
    <Dropdown onSelect={handleSelect}>
      <Dropdown.Toggle variant="success" id="dropdown-basic">
        {selectedOption}
      </Dropdown.Toggle>
      <Dropdown.Menu>
        {options.map((op) => (
          <Dropdown.Item key={op.id} eventKey={op.id.toString()}>
            {op.name}
          </Dropdown.Item>
        ))}
      </Dropdown.Menu>
    </Dropdown>
  );
};

export default DropdownComponent;
