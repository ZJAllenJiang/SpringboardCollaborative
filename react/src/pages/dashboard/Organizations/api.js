import axios from 'axios';

export const getOrganizations = () => {
    return axios.get('/api/organizations');
}
export const getOrganization = (id) => {
    return axios.get(`/api/organizations/${id}`);
}
export const createOrganization = (data) => {
    return axios.post('/api/organizations', data);
}
export const updateOrganization = (id, data) => {
    return axios.put(`/api/organizations/${id}`, data);
}
export const deleteOrganization = (id) => {
    return axios.delete(`/api/organizations/${id}`);
}
