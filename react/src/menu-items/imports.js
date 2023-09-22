// assets
import { IconOld, IconAffiliate } from '@tabler/icons';


// ==============================|| DASHBOARD MENU ITEMS ||============================== //

const imports = {
  id: 'batch',
  title: 'Batch',
  type: 'group',
  children: [
    {
      id: 'batch-recipients',
      title: 'Recipients',
      type: 'item',
      url: '/batch/recipients',
      icon: IconOld,
      breadcrumbs: false
    },
    {
      id: 'batch-organizations',
      title: 'Organizations',
      type: 'item',
      url: '/batch/organizations',
      icon: IconAffiliate,
      breadcrumbs: false
    }
  ]
};

export default imports;
