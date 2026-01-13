import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(), // optional: global error logging
    provideRouter(routes),                 // routing for your app
    provideHttpClient(),                   // needed for HttpClient in your services
  ]
};
