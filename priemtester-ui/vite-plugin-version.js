import { readFileSync } from 'fs';
import { resolve, dirname } from 'path';
import { fileURLToPath } from 'url';

const __dirname = dirname(fileURLToPath(import.meta.url));

function versionPlugin() {
    return {
        name: 'version-plugin',
        config() {
            const packageJson = JSON.parse(readFileSync(resolve(__dirname, 'package.json'), 'utf-8'));
            return {
                define: {
                    'process.env.VERSION': JSON.stringify(packageJson.version)
                }
            };
        }
    }
}

export default versionPlugin;