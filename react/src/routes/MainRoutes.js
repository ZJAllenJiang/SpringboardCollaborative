import { lazy } from 'react';
import { useParams } from 'react-router-dom';

// project imports
import MainLayout from 'layout/MainLayout';
import Loadable from 'ui-component/Loadable';

// dashboard routing
const DashboardDefault = Loadable(
  lazy(() => import('pages/dashboard/Default'))
);
const DashboardMatch = Loadable(lazy(() => import('pages/dashboard/Match')));
const DashboardCases = Loadable(lazy(() => import('pages/dashboard/Cases')));
const DashboardCasesEdit = Loadable(
  lazy(() => import('pages/dashboard/Cases/Edit'))
);
const DashboardRecipients = Loadable(
  lazy(() => import('pages/dashboard/Recipients'))
);
const DashboardRecipientsEdit = Loadable(
  lazy(() => import('pages/dashboard/Recipients/Edit'))
);
const DashboardOrganizations = Loadable(
  lazy(() => import('pages/dashboard/Organizations'))
);
const DashboardOrganizationsEdit = Loadable(
  lazy(() => import('pages/dashboard/Organizations/Edit'))
);

// import routing
const Batch = Loadable(lazy(() => import('pages/batch')));

// utilities routing
const UtilsTypography = Loadable(
  lazy(() => import('pages/utilities/Typography'))
);
const UtilsColor = Loadable(lazy(() => import('pages/utilities/Color')));
const UtilsShadow = Loadable(lazy(() => import('pages/utilities/Shadow')));
const UtilsMaterialIcons = Loadable(
  lazy(() => import('pages/utilities/MaterialIcons'))
);
const UtilsTablerIcons = Loadable(
  lazy(() => import('pages/utilities/TablerIcons'))
);

// sample page routing
const SamplePage = Loadable(lazy(() => import('pages/sample-page')));

// ==============================|| MAIN ROUTING ||============================== //

const MainRoutes = {
  path: '/',
  element: <MainLayout />,
  children: [
    {
      path: '/',
      element: <DashboardDefault />,
    },
    {
      path: 'dashboard',
      children: [
        {
          path: '',
          element: <DashboardDefault />,
        },
        {
          path: 'match',
          element: <DashboardMatch />,
        },
        {
          path: 'cases',
          children: [
            {
              path: '',
              element: <DashboardCases />,
            },
            {
              path: 'add',
              element: <DashboardCasesEdit />,
            },
            {
              path: 'edit/:id',
              element: <DashboardCasesEdit />,
            },
          ],
        },
        {
          path: 'recipients',
          children: [
            {
              path: '',
              element: <DashboardRecipients />,
            },
            {
              path: 'add',
              element: <DashboardRecipientsEdit />,
            },
            {
              path: 'edit/:id',
              element: <DashboardRecipientsEdit />,
            },
          ],
        },
        {
          path: 'organizations',
          children: [
            {
              path: '',
              element: <DashboardOrganizations />,
            },
            {
              path: 'add',
              element: <DashboardOrganizationsEdit />,
            },
            {
              path: 'edit/:id',
              element: <DashboardOrganizationsEdit />,
            },
          ],
        },
      ],
    },
    {
      path: 'batch',
      children: [
        {
          path: 'recipients',
          element: <Batch />,
        },
        {
          path: 'organizations',
          element: <Batch />,
        },
      ],
    },
    {
      path: 'utils',
      children: [
        {
          path: 'util-typography',
          element: <UtilsTypography />,
        },
      ],
    },
    {
      path: 'utils',
      children: [
        {
          path: 'util-color',
          element: <UtilsColor />,
        },
      ],
    },
    {
      path: 'utils',
      children: [
        {
          path: 'util-shadow',
          element: <UtilsShadow />,
        },
      ],
    },
    {
      path: 'icons',
      children: [
        {
          path: 'tabler-icons',
          element: <UtilsTablerIcons />,
        },
      ],
    },
    {
      path: 'icons',
      children: [
        {
          path: 'material-icons',
          element: <UtilsMaterialIcons />,
        },
      ],
    },
    {
      path: 'sample-page',
      element: <SamplePage />,
    },
  ],
};

export default MainRoutes;
