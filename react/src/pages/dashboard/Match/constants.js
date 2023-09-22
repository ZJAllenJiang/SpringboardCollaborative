export const mockMatchOverlap = {
    
    series: [{
        name: 'FSCAA',
        data: [80, 50, 30, 40, 100, 20],
    }, {
        name: 'Marigold',
        data: [20, 30, 40, 80, 20, 80],
    }, {
        name: 'ACT Team',
        data: [44, 76, 78, 13, 43, 10],
    }],
    options: {
        chart: {
            id: 'radar-chart',
            height: 500,
            type: 'radar',
            dropShadow: {
                enabled: true,
                blur: 1,
                left: 1,
                top: 1
            }
        },
        title: {
            text: 'Radar Chart - Multi Series'
        },
        stroke: {
            width: 2
        },
        fill: {
            opacity: 0.1
        },
        markers: {
            size: 0
        },
        xaxis: {
            categories: ['Housing', 'Medical', 'Dental', 'Mental Health', 'Addiction Treatment', 'Education']
        }
    },
}