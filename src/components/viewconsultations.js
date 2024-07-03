import React, { useState } from 'react';
import axios from 'axios';
import { Container, Form, Button, Table } from 'react-bootstrap';

const ViewConsultationsPage = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [consultations, setConsultations] = useState([]);
  const [showErrorAlert, setShowErrorAlert] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Fetch patient ID based on first and last name
      const patientResponse = await axios.get('http://localhost:8080/patients/getallbyname', {
        params: { firstName, lastName }
      });

      if (patientResponse.data.length === 0) {
        // Handle case when patient is not found
        return;
      }

      const patientId = patientResponse.data[0].patient_id;
    

      console.log(patientId);

      const appointmentResponse = await axios.get(`http://localhost:8080/appointments/${patientId}`);

      if (appointmentResponse.data.length === 0) {
        setErrorMessage('Appointment not found for this patient.');
        setShowErrorAlert(true);
        return;
      }

      const appointmentId = appointmentResponse.data[0].appointment_id;
      console.log(appointmentId);
      // Retrieve consultations for the patient
      const consultationsResponse = await axios.get(`http://localhost:8080/consultations/${appointmentId}`);

      setConsultations(consultationsResponse.data);
    } catch (error) {
      console.error('Error fetching consultations:', error);
    }
  };

  return (
    <Container className="my-5">
      <h2 className="mb-4">View Consultations</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="firstName">
          <Form.Label>First Name</Form.Label>
          <Form.Control
            type="text"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="lastName">
          <Form.Label>Last Name</Form.Label>
          <Form.Control
            type="text"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
          />
        </Form.Group>
        <Button variant="primary" type="submit" className="mt-3">
          View Consultations
        </Button>
      </Form>
      {consultations.length > 0 && (
        <Table striped bordered hover className="mt-4">
          <thead>
            <tr>
              <th>Consultation ID</th>
              <th>Appointment Date</th>
              <th>Appointment Time</th>
              <th>Consultation Date</th>
              <th>Consultation Note</th>
              <th>Presctiption</th>
            </tr>
          </thead>
          <tbody>
            {consultations.map((consultation) => (
              <tr key={consultation.consultationId}>
                <td>{consultation.consultationId}</td>
                <td>{consultation.appointmentDate}</td>
                <td>{consultation.appointmentTime}</td>
                <td>{consultation.consultationDate}</td>
                <td>{consultation.consultationNotes}</td>
                <td>{consultation.presctiption}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      )}
    </Container>
  );
};

export default ViewConsultationsPage;
