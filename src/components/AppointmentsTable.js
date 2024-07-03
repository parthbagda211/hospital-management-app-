import React, { useEffect, useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, Modal, Form } from 'react-bootstrap';

const ExampleComponent = () => {
  const [appointments, setAppointments] = useState([]);
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [selectedAppointment, setSelectedAppointment] = useState(null);
  const [updatedAppointmentDate, setUpdatedAppointmentDate] = useState('');
  const [updatedAppointmentTime, setUpdatedAppointmentTime] = useState('');

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const response = await axios.get('http://localhost:8080/appointments/getallapp');
        setAppointments(response.data);
      } catch (error) {
        console.error('Error fetching appointments:', error);
      }
    };

    fetchAppointments();
  }, []);

  const handleUpdateAppointment = (appointment) => {
    setSelectedAppointment(appointment);
    setUpdatedAppointmentDate(appointment.appointmentDate);
    setUpdatedAppointmentTime(appointment.appointmentTime);
    setShowUpdateModal(true);
  };

  const handleSaveUpdatedAppointment = async () => {
    try {
      const updatedAppointment = {
        appointmentId: selectedAppointment.appointmentId,
        appointmentDate: updatedAppointmentDate,
        appointmentTime: updatedAppointmentTime,
      };

      await axios.put(`http://localhost:8080/appointments/${selectedAppointment.appointmentId}`, updatedAppointment);
      setShowUpdateModal(false);

      // Refresh the appointments list
      const response = await axios.get('http://localhost:8080/appointments/getallapp');
      setAppointments(response.data);
    } catch (error) {
      console.error('Error updating appointment:', error);
    }
  };

  const handleDeleteAppointment = async (appointmentId) => {
    try {
      await axios.delete(`http://localhost:8080/appointments/${appointmentId}`);

      // Refresh the appointments list
      const response = await axios.get('http://localhost:8080/appointments/getallapp');
      setAppointments(response.data);
    } catch (error) {
      console.error('Error deleting appointment:', error);
    }
  };

  return (
    <div className="container my-5">
      <h2 className="mb-4">Appointments</h2>
      <table className="table table-striped table-bordered table-hover">
        <thead className="table-dark">
          <tr>
            <th>Appointment ID</th>
            <th>Patient Name</th>
            <th>Doctor Name</th>
            <th>Appointment Date</th>
            <th>Appointment Time</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {appointments.map((appointment) => (
            <tr key={appointment.appointmentId}>
              <td>{appointment.appointmentId}</td>
              <td>{`${appointment.patient.firstName} ${appointment.patient.lastName}`}</td>
              <td>{`${appointment.doctor.firstName} ${appointment.doctor.lastName}`}</td>
              <td>{appointment.appointmentDate}</td>
              <td>{appointment.appointmentTime}</td>
              <td>
                <Button variant="primary" style={{ marginRight: '1rem' }} onClick={() => handleUpdateAppointment(appointment)}>
                  Update
                </Button>
                <Button variant="danger" className="me-3" onClick={() => handleDeleteAppointment(appointment.appointmentId)}>
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <Modal show={showUpdateModal} onHide={() => setShowUpdateModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Update Appointment</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="appointmentDate">
              <Form.Label>Appointment Date</Form.Label>
              <Form.Control
                type="date"
                value={updatedAppointmentDate}
                onChange={(e) => setUpdatedAppointmentDate(e.target.value)}
              />
            </Form.Group>
            <Form.Group controlId="appointmentTime">
              <Form.Label>Appointment Time</Form.Label>
              <Form.Control
                type="time"
                value={updatedAppointmentTime}
                onChange={(e) => setUpdatedAppointmentTime(e.target.value)}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowUpdateModal(false)}>
            Cancel
          </Button>
          <Button variant="primary" onClick={handleSaveUpdatedAppointment}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default ExampleComponent;
