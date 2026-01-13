


// import axios from "axios";

// const API_URL = 'http://localhost:8080/api';

// const api = axios.create({
//     baseURL: API_URL,
//     withCredentials: true
// });

// api.interceptors.request.use((config) => {
//     const userId = localStorage.getItem('userId');
//     const token = localStorage.getItem('token');

//     if (!token) {
//         console.warn("No token found! API request may fail.");
//         // Optionally: throw new Error("User not authenticated");
//     }

//     if (token) {
//         config.headers['Authorization'] = `Bearer ${token}`;
//     }

//     if (userId) {
//         config.headers['X-User-ID'] = userId;
//     }

//     return config;
// }, (error) => {
//     return Promise.reject(error);
// });


// export const getActivities = () => api.get('/activities');
// export const addActivity = (activity) => api.post('/activities', activity);
// // export const getActivityDetails = (id) => api.get(`/recommendations/activity/${id}`);
// export const getActivityDetails = (id) => api.get(`/recommendation/activity/${id}`);
// // export const getActivityDetails = (id) => api.get(`/recommendations/activity/${id}`);



import axios from "axios";

const API_URL = 'http://localhost:8080/api';

const api = axios.create({
    baseURL: API_URL,
    withCredentials: true
});

api.interceptors.request.use((config) => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }

    if (userId) {
        config.headers['X-User-ID'] = userId;
    }

    return config;
}, (error) => Promise.reject(error));

export const getActivities = () => api.get('/activities');
export const addActivity = (activity) => api.post('/activities', activity);
export const getActivityDetails = (id) => api.get(`/recommendation/activity/${id}`);
