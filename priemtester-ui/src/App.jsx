import './App.css';
import PriemGetalTester from "./priemtester/PriemgetalTester";
import PriemFetchService from './priemtester/PriemFetchService.js';

function App() {
  const priemFetchService = new PriemFetchService();
  return (
    <div className="App">
      <header className="App-header">
        <PriemGetalTester priemFetchService={priemFetchService} />
      </header>
    </div>
  );
}

export default App;
