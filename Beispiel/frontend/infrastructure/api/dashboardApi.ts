/*
 * =====================================================================================
 * [Squad P] KOPIERT AUS: frontend/src/data/api/dashboardApi.ts
 * NEUE SCHICHT: infrastructure/api  (überschreibt den gleichnamigen leeren Platzhalter)
 * -------------------------------------------------------------------------------------
 * WAS JETZT ZU TUN IST:
 *
 * 1. Die Typen ChartDataDTO, AktivitaetDTO, AufmerksamkeitDTO, DashboardStatsResponse
 *    beschreiben den Daten-State des Dashboards -> nach
 *    Beispiel/frontend/Model/dashboard/DashboardSummary.ts (Platzhalter) verschieben und
 *    hier importieren (gleiches Vorgehen wie bei Model/user/UserProfile.ts).
 *
 * 2. OFFEN / mit Squad J klären: Die Dashboard-Daten kommen aus dem offer-service
 *    (GET {OFFER_SERVICE_URL}/dashboard) - das ist Squad J. team-ownership.md nennt
 *    zusätzlich einen "dashboard-service" für Team 1, den es noch nicht gibt.
 *    -> Entscheiden, wer den Endpoint besitzt (offer-service bleibt / eigener Service).
 *
 * 3. Fetch + Token + Fehlerbehandlung über den gemeinsamen
 *    Beispiel/frontend/infrastructure/http/apiClient.ts (Platzhalter) laufen lassen,
 *    siehe Punkt 2 in infrastructure/api/userService.ts.
 *
 * 4. Imports anpassen (bewusst noch NICHT geändert):
 *      "@/config/api"           -> nicht kopiert (frontend/src/config/api.ts, gemeinsam P + J)
 *      "@/services/authService" -> Beispiel/frontend/infrastructure/auth/authService.ts
 * =====================================================================================
 */
import { API_CONFIG } from "@/config/api";
import { getToken } from "@/services/authService";

export interface ChartDataDTO {
  month: string;
  angebote: number;
}

export interface AktivitaetDTO {
  offerId: number;
  businessKey: string;
  customerId: number;
  status: string;
  zeitpunkt: string;
}

export interface AufmerksamkeitDTO {
  offerId: number;
  businessKey: string;
  customerId: number;
  versendetAm: string;
}

export interface DashboardStatsResponse {
  angeboteGesamt: number;
  ohneRueckmeldung: number;
  mitRueckmeldung: number;
  nichtFertiggestellt: number;
  rechnungenAusgestellt: number;
  rechnungenBezahlt: number;
  rechnungsvolumen: number;
  letzteAktivitaeten: AktivitaetDTO[];
  aufmerksamkeitErforderlich: AufmerksamkeitDTO[];
  angebotsuebersicht: ChartDataDTO[];
}

const API_BASE_URL = API_CONFIG.OFFER_SERVICE_URL; // Assuming the dashboard stats are fetched from the offer service

export const getDashboardStats = async (): Promise<DashboardStatsResponse> => {
  const token = await getToken();

  const response = await fetch(`${API_BASE_URL}/dashboard`, {
    method: "GET",
    headers: {
      ...(token && { Authorization: `Bearer ${token}` }),
    },
  });

  if (!response.ok) {
    throw new Error(`Failed to fetch dashboard stats: ${response.statusText}`);
  }

  return response.json();
};
