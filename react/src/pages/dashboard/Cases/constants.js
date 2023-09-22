
import {
    randomCreatedDate,
    randomTraderName,
    randomId,
    randomArrayItem,
  } from '@mui/x-data-grid-generator';

export const mockCases = [
    {
        taskId: "12301",
        recipientId: "4301",
        tscAdministratorId: "21302",
        navigatorId: "314",
        taskProgressEnum: "START", //START, IN_PROGRESS, COMPLETED
        consenus: ['consent1.pdf', 'consent2.pdf'], 
        appointment: {
            time: "2023 Oct 2 10:00 AM",
            url: ''
        }
    },
    {
        taskId: "12302",
        recipientId: "4302",
        tscAdministratorId: "21304",
        navigatorId: "313",
        taskProgressEnum: "START",
        consenus: [], 
        appointment: {
            time: "2023 Sep 29 12:30 AM",
            url: ''
        }
    },
    {
        taskId: "12303",
        recipientId: "4303",
        tscAdministratorId: "21302",
        navigatorId: "315",
        taskProgressEnum: "IN_PROGRESS",
        consenus: ['consent1.pdf', 'consent2.pdf'], 
        appointment: {
            time: "2023 Nov 3 10:15 AM",
            url: ''
        }
    },
    {
        taskId: "12304",
        recipientId: "4301",
        tscAdministratorId: "21303",
        navigatorId: "312",
        taskProgressEnum: "COMPLETED",
        consenus: ['consent1.pdf'], 
        appointment: {
            time: "2023 Sep 30 15:45 AM",
            url: ''
        }
    },
    {
        taskId: "12305",
        recipientId: "4302",
        tscAdministratorId: "21305",
        navigatorId: "311",
        taskProgressEnum: "COMPLETED",
        consenus: [], 
        appointment: {
            time: "2023 Oct 16 14:30 AM",
            url: ''
        }
    },
    {
        taskId: "12306",
        recipientId: "4303",
        tscAdministratorId: "21301",
        navigatorId: "311",
        taskProgressEnum: "IN_PROGRESS",
        consenus: ['consent2.pdf'], 
        appointment: {
            time: "2023 Oct 1 17:00 AM",
            url: ''
        }
    },
    {
        taskId: "12307",
        recipientId: "4301",
        tscAdministratorId: "21302",
        navigatorId: "314",
        taskProgressEnum: "START",
        consenus: [], 
        appointment: {
            time: "2023 Sep 21 19:30 AM",
            url: ''
        }
    },
    {
        taskId: "12308",
        recipientId: "4302",
        tscAdministratorId: "21304",
        navigatorId: "313",
        taskProgressEnum: "START",
        consenus: ['consent1.pdf', 'consent3.pdf'], 
        appointment: {
            time: "2023 Nov 18 10:40 AM",
            url: ''
        }
    },
    {
        taskId: "12309",
        recipientId: "4303",
        tscAdministratorId: "21302",
        navigatorId: "315",
        taskProgressEnum: "IN_PROGRESS",
        consenus: ['consent1.pdf', 'consent2.pdf', 'consent3.pdf'], 
        appointment: {
            time: "2023 Oct 11 20:30 AM",
            url: ''
        }
    },
    {
        taskId: "12310",
        recipientId: "4301",
        tscAdministratorId: "21303",
        navigatorId: "312",
        taskProgressEnum: "COMPLETED",
        consenus: [],
        appointment: {
            time: "2023 Sep 23 16:45 AM",
            url: ''
        }
    },
    {
        taskId: "12311",
        recipientId: "4302",
        tscAdministratorId: "21305",
        navigatorId: "311",
        taskProgressEnum: "COMPLETED",
        consenus: [], 
        appointment: {
            time: "2023 Oct 27 15:15 AM",
            url: ''
        }
    },
    {
        taskId: "12312",
        recipientId: "4303",
        tscAdministratorId: "21301",
        navigatorId: "311",
        taskProgressEnum: "IN_PROGRESS",
        consenus: ['consent1.pdf', 'consent2.pdf'], 
        appointment: {
            time: "2023 Nov 2 18:20 AM",
            url: ''
        }
    }
]