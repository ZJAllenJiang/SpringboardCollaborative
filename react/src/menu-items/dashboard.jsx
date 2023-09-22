// assets
import { IconDashboard, IconChecklist, IconOld, IconAffiliate } from '@tabler/icons';


// ==============================|| DASHBOARD MENU ITEMS ||============================== //

const dashboard = {
  id: 'dashboard',
  title: 'Dashboard',
  type: 'group',
  children: [
    {
      id: 'dashboard-default',
      title: 'Dashboard',
      type: 'item',
      url: '/dashboard',
      icon: IconDashboard,
      breadcrumbs: false
    },
    {
      id: 'dashboard-tasks',
      title: 'Cases',
      type: 'item',
      url: '/dashboard/Cases',
      icon: IconChecklist,
      breadcrumbs: false
    },
    {
      id: 'dashboard-recipients',
      title: 'Recipients',
      type: 'item',
      url: '/dashboard/recipients',
      icon: IconOld,
      breadcrumbs: false
    },
    {
      id: 'dashboard-organizations',
      title: 'Organizations',
      type: 'item',
      url: '/dashboard/organizations',
      icon: IconAffiliate,
      breadcrumbs: false
    }
  ]
};

export default dashboard;
