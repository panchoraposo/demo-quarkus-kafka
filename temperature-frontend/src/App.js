import React, { useEffect, useState } from 'react';
import Gauge from './Gauge';

const App = () => {
    const [data, setData] = useState({ temperature: 0, humidity: 0 });
    const endpoint = "http://localhost:8082/api/temperature";

    useEffect(() => {
      const fetchData = async () => {
        try {
            const response = await fetch(endpoint);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const result = await response.json();
            console.log('Fetched data:', result); // Depuración
    
            // Toma el último elemento del arreglo
            const latestData = result[result.length - 1];
            if (latestData) {
                setData({
                    temperature: latestData.temperature || 0,
                    humidity: latestData.humidity || 0,
                });
            } else {
                console.warn('No data available in the array');
            }
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

        const interval = setInterval(fetchData, 2000);
        return () => clearInterval(interval);
    }, [endpoint]);

    return (
        <div style={{ textAlign: 'center', fontFamily: 'Arial, sans-serif' }}>
            <div style={{ display: 'flex', justifyContent: 'center', gap: '20px' }}>
                <div>
                    <h2>Temperature</h2>
                    <Gauge value={data.temperature} min={0} max={50} color="orange" label="°F" />
                </div>
                <div>
                    <h2>Humidity</h2>
                    <Gauge value={data.humidity} min={0} max={100} color="blue" label="%" />
                </div>
            </div>
        </div>
    );
};

export default App;