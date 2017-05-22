CREATE TABLE mail_hist (
  id      SERIAL PRIMARY KEY,
  list_to TEXT NULL,
  list_cc TEXT NULL,
  subject TEXT NULL,
  body    TEXT NULL,
  state   TEXT NOT NULL,
  date    TIMESTAMP NOT NULL
);

COMMENT ON TABLE mail_hist IS 'История отправки email';
COMMENT ON COLUMN mail_hist.date IS 'Время отправки';