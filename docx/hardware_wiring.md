Based on your equipment list for **PlantIntelligence**, here is the complete component pinout and hardware wiring documentation incorporating all the items from Phase 01 and Phase 02:

### Hardware Wiring & Component Pinout Documentation (#10)

#### 1. Pinout Connection Table

| Component | Component Pin | ESP32 Pin | Description |
| --- | --- | --- | --- |
| **DHT22 Temp & Humidity Sensor** | VCC | 3.3V | Power Supply |
|  | GND | GND | Ground |
|  | DATA | GPIO 4 | Digital Data Signal |
| **Capacitive Soil Moisture v2.0** | VCC | 3.3V or 5V | Power Supply |
|  | GND | GND | Ground |
|  | AOUT | GPIO 34 (ADC1_6) | Analog Moisture Reading |
| **BH1750FVI Light Intensity Sensor** | VCC | 3.3V | Power Supply |
|  | GND | GND | Ground |
|  | SCL | GPIO 22 | I2C Clock |
|  | SDA | GPIO 21 | I2C Data |
| **1-Channel 5V Relay Module** | VCC | 5V (VIN) | Power Supply |
|  | GND | GND | Ground |
|  | IN | GPIO 26 | Control Trigger Signal |

---

#### 2. Wiring & Power Distribution Notes

* **Power Rails:** Use the breadboard rails to split the 3.3V output from the ESP32 for the DHT22 and BH1750 sensors. The 1-Channel Relay module requires a 5V supply, which should be sourced from the ESP32's `VIN` (or `5V`) pin when connected via USB power.
* **Water Pump & Relay Safety:** The Mini Submersible Pump connects through the relay's **COM** (Common) and **NO** (Normally Open) terminals, driven by an external power source through the DC Jack. Ensure you do not draw pump power directly from the ESP32 5V pin to prevent voltage drops.
* **Analog Conversion:** GPIO 34 is utilized for the capacitive soil moisture sensor because it belongs to ADC1, ensuring stable analog-to-digital readings even when the ESP32 Wi-Fi stack is actively transmitting data.