import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/appointments/getallapp';

const xyz = ()=>{
    
    console.log(axios.get(REST_API_BASE_URL));
}
