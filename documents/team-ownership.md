# Zuständigkeiten

## Squad P – Handwerker

- Oberflächen für Login und benutzerbezogene Funktionen
- user-service und dessen Datenbank
- ai-service und dessen vorgesehene Datenhaltung
- Prozessanbindung dieser Services gemeinsam mit Squad V

## Squad J – Auftraggeber

- Oberflächen für Kunden, Anträge und Angebote
- offer-service und dessen Datenbank
- document-service und dessen Datenbank
- Dokumente zu Kunden, Anträgen und Angeboten
- Prozessanbindung dieser Services gemeinsam mit Squad V

## Squad V – BPMN und Process Engine

- BPMN-Modelle und deren Bereitstellung
- Einrichtung und Konfiguration der Process Engine
- Prozessanbindung gemeinsam mit den Service-Teams
- Nachrichten, Prozesszuordnung und Fehlerbehandlung abstimmen

## Gemeinsam – Squad P und Squad J

- MVVM-Grundgerüst und gemeinsame Frontend-Komponenten
- API-Client und Backend-Anbindung
- catalog-service und dessen Datenbank
- Gateway: gemeinsamer Aufbau durch Patrick und Jan

## Noch abzustimmen

- Konkrete Schnittstellen und Datenformate unter contracts/
- Verantwortung für Kunden- und Unternehmensdaten
- Aufteilung der Katalogarbeiten
- Keycloak-Anbindung und Berechtigungen
- Gemeinsame Startumgebung und Bereitstellung





# Team-Ownership

## Team 1: Handwerker/User

- `frontend/View/user`
- `frontend/ViewModel/user`
- `frontend/View/dashboard`
- `frontend/ViewModel/dashboard`
- `frontend/View/voice`
- `frontend/ViewModel/voice`
- `frontend/Model/user`
- `frontend/Model/dashboard`
- `frontend/Model/voice`
- `backend/services/user-service`
- `backend/services/dashboard-service`
- `backend/services/ai-service`

Team 1 besitzt Handwerkerkonto, Authentifizierung, Profil, Dashboard,
Sprachaufnahme und KI-Analyse.

## Team 2: Kunde/Auftrag

- `frontend/View/customer`
- `frontend/ViewModel/customer`
- `frontend/View/offer`
- `frontend/ViewModel/offer`
- `frontend/View/document`
- `frontend/ViewModel/document`
- `frontend/Model/customer`
- `frontend/Model/offer`
- `frontend/Model/document`
- `backend/services/customer-service`
- `backend/services/offer-service`
- `backend/services/catalog-service`
- `backend/services/document-service`

Team 2 besitzt Kunden, Aufträge, Angebote, Positionen, Preise, Katalog,
Rechnungen, PDFs und Versand.

## Team 3: Prozesse

- `processengine/`
- `contracts/workflow`

Team 3 besitzt BPMN, Process Engine, technische Connectoren, Message-Korrelation,
Business Keys, Retries, Timeouts und Prozessversionen.