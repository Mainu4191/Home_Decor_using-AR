export class EnvService {
    API_URL;
    constructor() {
        this.API_URL = 'http://192.168.232.176:8080';
    }

    getApiUrl() {
        return this.API_URL;
    }


}