import { describe, it, expect, vi, afterEach } from 'vitest';
import PriemFetchService from './PriemFetchService';

vi.stubGlobal('fetch', vi.fn()); // Mock fetch globally

describe('PriemFetchService', () => {
    const service = new PriemFetchService();

    afterEach(() => {
        vi.restoreAllMocks(); // Reset mocks na elke test
    });

    it('should fetch prime number data successfully', async () => {
        const mockResponse = {
            isPriem: true,
        };

        fetch.mockResolvedValueOnce({
            ok: true,
            json: async () => mockResponse,
        });

        const result = await service.checkPriem(7);

        expect(fetch).toHaveBeenCalledWith(import.meta.env.VITE_API_BASE_URL + 'priem', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ number: 7 }),
        });

        expect(result).toEqual(mockResponse);
    });

    it('should throw an error for non-OK responses', async () => {
        fetch.mockResolvedValueOnce({
            ok: false,
            status: 500,
        });

        await expect(service.checkPriem(7)).rejects.toThrow('Failed to fetch');
    });

    it('should throw an error for network issues', async () => {
        fetch.mockRejectedValueOnce(new Error('Network error'));

        await expect(service.checkPriem(7)).rejects.toThrow('Network error');
    });
});
