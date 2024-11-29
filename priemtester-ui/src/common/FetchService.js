export default class FetchService {

  constructor(baseUrl) {
    this.baseUrl = baseUrl;
    this.isOnline = true;
    this.pollingInterval = 5000; // Poll elke 5 seconden
  }

  async fetchWithCheck(endpoint, options = {}) {
    try {
      const response = await fetch(`${this.baseUrl}${endpoint}`, options);

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      this.isOnline = true; // Server is online
      return response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      this.isOnline = false;
      throw error;
    }
  }

  async checkServerStatus() {
    try {
      const response = await fetch(`${this.baseUrl}/health`); // Backend health endpoint
      this.isOnline = response.ok;
    } catch {
      this.isOnline = false;
    }
  }

  startPolling() {
    setInterval(() => this.checkServerStatus(), this.pollingInterval);
  }
}
