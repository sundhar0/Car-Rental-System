
import React, { useEffect, useState } from 'react';
import { Bar, Pie } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  ArcElement,
  BarElement,
  CategoryScale,
  LinearScale,
  Tooltip,
  Legend
} from 'chart.js';

ChartJS.register(ArcElement, BarElement, CategoryScale, LinearScale, Tooltip, Legend);

const ActiveChartDashboard = ({ sell }) => {
  const [barData, setBarData] = useState(null);
  const [revenueData, setRevenueData] = useState(null);

  useEffect(() => {
    if (!sales || !sales.length) return;

    const modelCount = {};
    const statusCount = { Completed: 0, InProgress: 0 };
    const revenueByModel = {};

    sales.forEach(sell => {
      const model = `${sell.car?.carMake} ${sell.car?.carModel}`;
      modelCount[model] = (modelCount[model] || 0) + 1;
      // revenueByModel[model] = (revenueByModel[model] || 0) + rental.cost;

      if (sell.status === 'Completed') statusCount.Completed++;
      else statusCount.InProgress++;
    });

    setBarData({
      labels: Object.keys(modelCount),
      datasets: [{
        label: 'Selling Count',
        data: Object.values(modelCount),
        backgroundColor: 'rgba(75,192,192,0.6)',
        borderColor: 'rgba(75,192,192,1)',
        borderWidth: 1
      }]
    });

 

    setRevenueData({
      labels: Object.keys(revenueByModel),
      datasets: [{
        label: 'Total Revenue ($)',
        data: Object.values(revenueByModel),
        backgroundColor: 'rgba(153, 102, 255, 0.6)',
        borderColor: 'rgba(153, 102, 255, 1)',
        borderWidth: 1
      }]
    });
  }, [sales]);

  return (
    <div className="container my-5">
      <h3 className="mb-4">Seller Analytics</h3>
      <div className="d-flex flex-wrap justify-content-between gap-3">
        <div style={{ flex: '1 1 30%', minWidth: '300px' }}>
          <h5>Sell Count by Car Model</h5>
          {barData?.datasets?.length ? <Bar data={barData} /> : <p>No data available</p>}
        </div>
        <div style={{ flex: '1 1 30%', minWidth: '300px' }}>
          <h5>Revenue by Car Model</h5>
          {revenueData?.datasets?.length ? <Bar data={revenueData} /> : <p>No data available</p>}
        </div>
      </div>
    </div>
  );
};

export default ActiveChartDashboard;
