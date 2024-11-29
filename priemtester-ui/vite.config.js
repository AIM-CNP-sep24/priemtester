import { defineConfig } from 'vite';
import versionPlugin from "./vite-plugin-version.js";
import react from '@vitejs/plugin-react';

export default defineConfig({
    plugins: [
        react(),
        versionPlugin()
    ],
    // You don't need to configure anything in vite.config.js for .env files to work.
    // However, below 'define' allows to provide a fallback for undefined variables or customize.
    define: {
        'process.env': { ...process.env, ...import.meta.env }
    },
    test: {
        globals: true,
        environment: 'jsdom',
        setupFiles: './testSetup.js', // Optional: For custom setups
    },
    build: {
        outDir: '../src/main/resources/static', // Correct directory
        emptyOutDir: false, // Clear old files
    }
});

