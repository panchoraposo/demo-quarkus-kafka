import React from 'react';
import * as d3 from 'd3';

const Gauge = ({ value, min, max, color, label }) => {
    const width = 200;
    const height = 150;
    const radius = Math.min(width, height) / 2;
    const arcGenerator = d3.arc()
        .innerRadius(radius - 20)
        .outerRadius(radius)
        .startAngle(-Math.PI / 2);

    const scale = d3.scaleLinear()
        .domain([min, max])
        .range([-Math.PI / 2, Math.PI / 2]);

    const backgroundArc = arcGenerator({ endAngle: Math.PI / 2 });
    const foregroundArc = arcGenerator({ endAngle: scale(value) });

    return (
        <svg width={width} height={height}>
            <path
                d={backgroundArc}
                fill="#ddd"
                transform={`translate(${width / 2}, ${height})`}
            />
            <path
                d={foregroundArc}
                fill={color}
                transform={`translate(${width / 2}, ${height})`}
            />
            <text
                x={width / 2}
                y={height - 20}
                textAnchor="middle"
                fontSize="16"
                fill="black"
            >
                {value.toFixed(1)} {label}
            </text>
        </svg>
    );
};

export default Gauge;