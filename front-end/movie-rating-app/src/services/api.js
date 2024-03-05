import axios from 'axios';

const baseURL = 'http://localhost:8080';

const api = axios.create({
    baseURL,
});

export const getMovies = async () => {
    const response = await api.get('/movies');
    return response.data;
};

export const getMoviesWithFilters = async (filters) => {
    try {
        const response = await api.get('/movies', {params: filters});
        return response.data;
    } catch (error) {
        throw new Error('Error fetching movies:', error);
    }
};

export const getMovieById = async (id) => {
    const response = await api.get(`/movies/${id}`);
    return response.data;
};

export const addMovie = async (movieData) => {
    const response = await api.post('/movies/add', movieData);
    return response.data;
};

export const addReviewToMovie = async (id, reviewData) => {
    const response = await api.post(`/movies/${id}/review`, reviewData);
    return response.data;
};