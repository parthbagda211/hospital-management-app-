import React from 'react';
import { useNavigate , Link,Router } from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import './MainContent.css';
// import ExampleComponent from './components/AppointmentsTable';

const MainContent = () => {
  const navigate = useNavigate();

  const handleViewAppointments = () => {
    navigate('/appointments');
  };
  const handleAddPatient = () => {
    navigate('/add-patient');
  };
  const handleAddAppointment = () => {
    navigate('/add-appointment');
  };
 const handleAddDoctors = ()=>{
     navigate('/add-doctor')
 };
 const handleViewConsultation = () =>{
    navigate('/view-consultation')
 }
  return (
    <div className="main-content-container">
      <Card className="custom-card">
        <Card.Body>
          <Card.Title>Add New Patients Here !!</Card.Title>
          <Button variant="primary" onClick={handleAddPatient}>Add Patients</Button>
        </Card.Body>
      </Card>

      <Card className="custom-card">
        <Card.Body>
          <Card.Title>Add Your Appointments Here !!</Card.Title>
          <Button variant="primary" onClick={handleAddAppointment}>Add Appointments</Button>
        </Card.Body>
      </Card>

      <Card className="custom-card">
        <Card.Body>
          <Card.Title>View All Appointments !!</Card.Title>
          <Button variant="primary" onClick={handleViewAppointments}>
            View Appointments
          </Button>
        </Card.Body>
      </Card>

      <Card className="custom-card">
        <Card.Body>
          <Card.Title>Add New Consultations here !!</Card.Title>
          <Button variant="primary">Add Consultations</Button>
        </Card.Body>
      </Card>

      <Card className="custom-card">
        <Card.Body>
          <Card.Title>View All Consultations</Card.Title>
          <Button variant="primary" onClick={handleViewConsultation}> View Consultations</Button>
        </Card.Body>
      </Card>
    
      <Card className="custom-card">
        <Card.Body>
          <Card.Title> Add Doctors Here !!</Card.Title>
          <Button variant="primary" onClick={handleAddDoctors}>Add Doctors</Button>
        </Card.Body>
      </Card>
    </div>
  );
};

export default MainContent;
