import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import MainContent from './components/MainContent';
import ExampleComponent from './components/AppointmentsTable';
import AddPatientPage from './components/addpatients';
import AddAppointmentPage from './components/addappointments';
import AddDoctorsPage from './components/adddoctors';
import ViewConsultationsPage from './components/viewconsultations';
const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainContent />} />
        <Route path="/appointments" element={<ExampleComponent />} />
        <Route path="/add-patient" element={<AddPatientPage />} />
        <Route path="/add-appointment" element={<AddAppointmentPage />} />
        <Route path="/add-doctor" element={<AddDoctorsPage />} />
        <Route path="/view-consultation" element={<ViewConsultationsPage />} />
        
      </Routes>
    </Router>
  );
};

export default App;
