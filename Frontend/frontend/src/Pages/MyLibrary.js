import { useEffect, useState } from "react";
import HeadingPage from "../component/HeadingPage";
import { Table, Button, Toast } from "react-bootstrap";

function MyLibrary() {
  const [libpackage, setPackage] = useState([]);
  const [message, setMessage] = useState(null);
  const [customer, setCustomer] = useState(null);
  const [hasBoughtPackage, setHasBoughtPackage] = useState(false); // New state to track if a package has been bought

  useEffect(() => {
    const customerId = localStorage.getItem('customerId');
    fetch(`http://localhost:8080/api/customers/${customerId}`)
      .then(res => res.json())
      .then(data => {
        setCustomer(data);
        // Check if the customer has already bought a package
        if (data.libraryPackage) {
          setHasBoughtPackage(true);
        }
      })
      .catch(error => console.error('Error fetching customer data:', error));
  }, []);

  useEffect(() => {
    fetch('http://localhost:8080/api/library/packages')
      .then(res => res.json())
      .then(data => setPackage(data))
      .catch(error => console.error('Error fetching library packages:', error));
  }, []); // Empty dependency array ensures this runs once after the initial render

  const handleBuyClick = (pkg,pkgId) => {
    const customerId = localStorage.getItem('customerId'); // Retrieve customer ID
    fetch(`http://localhost:8080/api/customers/${customerId}/library/${pkgId}`, {
      method: 'PUT',
    })
    .then(response => {
      if (response.ok) {
        setMessage({ type: 'success', text: `User purchased package ${pkg}` });
        // Update hasBoughtPackage state and refetch customer data
        setHasBoughtPackage(true);
        return fetch(`http://localhost:8080/api/customers/${customerId}`)
          .then(res => res.json())
          .then(data => setCustomer(data));
      } else {
        return response.text().then(errorMessage => {
          setMessage({ type: 'error', text: errorMessage || 'Failed to update library package.' });
        });
      }
    })
    .catch(error => {
      console.error('Error:', error);
      setMessage({ type: 'error', text: 'Error occurred while updating library package.' });
    });
  };

  return (
    <div>
      <HeadingPage title="Library Package" />
      <div>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Expiry Days</th>
              <th>Number of Books Allowed</th>
              <th>Cost</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {libpackage.map((pkg) => (
              <tr key={pkg.id}>
                <td>{pkg.id}</td>
                <td>{pkg.name}</td>
                <td>{pkg.days}</td>
                <td>{pkg.noofbooksallowed}</td>
                <td>{pkg.cost}</td>
                <td>
                  {hasBoughtPackage ? (
                    <Button variant="secondary" disabled>
                      Already bought
                    </Button>
                  ) : (
                    <Button variant="primary" onClick={() => handleBuyClick(pkg.name,pkg.id)}>
                      Buy
                    </Button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>

      {/* Display message if available */}
      {message && (
        <div className="toast-container">
          <Toast bg={message.type === 'success' ? 'success' : 'danger'} onClose={() => setMessage(null)} delay={3000} autohide>
            <Toast.Body>{message.text}</Toast.Body>
          </Toast>
        </div>
      )}
    </div>
  );
}

export default MyLibrary;
