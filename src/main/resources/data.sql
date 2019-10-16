DROP TABLE IF EXISTS report;

CREATE TABLE report (
    id INT AUTO_INCREMENT PRIMARY KEY,
    month VARCHAR(20) NOT NULL,
    site VARCHAR(50) NOT NULL,
    requests INT NOT NULL,
    impressions INT NOT NULL,
    clicks INT NOT NULL,
    conversions INT NOT NULL,
    revenue DECIMAL NOT NULL,
    CTR DECIMAL NOT NULL,
    CR DECIMAL NOT NULL,
    fill_rate DECIMAL NOT NULL,
    eCPM DECIMAL NOT NULL
);
