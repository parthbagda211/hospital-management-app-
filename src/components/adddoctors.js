import React, { useState } from 'react';
import axios from 'axios';
import { Container, Form, Button, Row, Col, Alert } from 'react-bootstrap';

const AddDoctorsPage = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [specialization, setSpecialization] = useState('');
  const [contactNumber, setContactNumber] = useState('');
  const [showSuccessAlert, setShowSuccessAlert] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newDoctor = {
      firstName,
      lastName,
      specialization
    };

    try {
      await axios.post('http://localhost:8080/doctors/adddoctor', newDoctor);
      setShowSuccessAlert(true);
      // Reset form fields
      setFirstName('');
      setLastName('');
      setSpecialization('');
    } catch (error) {
      console.error('Error adding doctor:', error);
    }
  };

  return (
    <Container className="my-5">
      <h2 className="mb-4">Add New Doctor</h2>
      {showSuccessAlert && (
        <Alert variant="success" onClose={() => setShowSuccessAlert(false)} dismissible>
          Doctor added successfully!
        </Alert>
      )}
      <Form onSubmit={handleSubmit}>
        <Row>
          <Col md={6}>
            <Form.Group controlId="firstName">
              <Form.Label>First Name</Form.Label>
              <Form.Control
                type="text"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
                required
              />
            </Form.Group>
          </Col>
          <Col md={6}>
            <Form.Group controlId="lastName">
              <Form.Label>Last Name</Form.Label>
              <Form.Control
                type="text"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                required
              />
            </Form.Group>
          </Col>
        </Row>
        <Form.Group controlId="specialization">
          <Form.Label>Specialization</Form.Label>
          <Form.Control
            type="text"
            value={specialization}
            onChange={(e) => setSpecialization(e.target.value)}
            required
          />


        </Form.Group>
        <Form.Group controlId="contactNumber">
          <Form.Label>Phone NUmber </Form.Label>
          <Form.Control
            type="text"
            value={contactNumber}
            onChange={(e) => setContactNumber(e.target.value)}
            required
          />

          
        </Form.Group>
        <Button variant="primary" type="submit" className="mt-3">
          Add Doctor
        </Button>
      </Form>
    </Container>
  );
};

export default AddDoctorsPage;
