CREATE SEQUENCE donors_id_seq;
ALTER TABLE donors ALTER COLUMN id SET DEFAULT nextval('donors_id_seq');
SELECT setval('donors_id_seq', COALESCE((SELECT MAX(id) FROM donors), 0));
