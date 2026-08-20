import time
import random
import requests
import json

# Backend API Endpoint (Update with your running server URL)
SERVER_URL = "http://localhost:8080/api/telemetry"

DEVICE_ID = "ESP32_Plant_Mock_01"

print(f"Starting ESP32 Mock Telemetry Script...")
print(f"Targeting endpoint: {SERVER_URL}\n")

try:
    while True:
        # Generate realistic mock sensor readings
        payload = {
            "deviceId": DEVICE_ID,
            "temperature": round(random.uniform(24.0, 32.5), 2),  # Celsius
            "humidity": round(random.uniform(60.0, 85.0), 2),     # Percentage
            "soilMoisture": round(random.uniform(30.0, 90.0), 2), # Percentage
            "lightLux": round(random.uniform(200.0, 1500.0), 2)   # Lux
        }

        headers = {"Content-Type": "application/json"}

        try:
            # Send HTTP POST request
            response = requests.post(SERVER_URL, data=json.dumps(payload), headers=headers)
            
            print(f"[{time.strftime('%Y-%m-%d %H:%M:%S')}] Sent Payload: {payload}")
            print(f"Response Status: {response.status_code} | Body: {response.text}\n")
            
        except requests.exceptions.ConnectionError:
            print(f"[{time.strftime('%Y-%m-%d %H:%M:%S')}] Error: Could not connect to the backend server. Is Spring Boot running?\n")
        except Exception as e:
            print(f"An unexpected error occurred: {e}\n")

        # Wait 5 seconds before sending the next telemetry batch
        time.sleep(5)

except KeyboardInterrupt:
    print("\nMock telemetry script stopped by user.")