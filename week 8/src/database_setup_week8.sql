CREATE TABLE Pet (
    petID SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    species VARCHAR(100) NOT NULL,

    petType VARCHAR(20) NOT NULL CHECK (petType IN ('Dog', 'Cat')),
    trained BOOLEAN,
    indoor BOOLEAN
);

CREATE INDEX idx_petType ON pet(petType);
CREATE INDEX idx_pet_name ON pet(name);

-- Test Dogs
INSERT INTO pet (name, age, species, petType, trained, indoor)
VALUES
    ('Aktos', 6, 'Dog', 'Dog', true, NULL),
    ('Aktaban', 3, 'Dog', 'Dog', false, NULL);

-- Test Cats
INSERT INTO pet (name, age, species, petType, trained, indoor)
VALUES
    ('Musya', 2, 'Cat', 'Cat', NULL, true),
    ('Barsik', 7, 'Cat', 'Cat', NULL, false);

-- Verify Data
SELECT * FROM pet ORDER BY petID;

-- Count all pets
SELECT COUNT(*) as total_pet FROM pet;

-- Get only dogs
SELECT * FROM pet WHERE petType = 'Dog';

-- Get only cats
SELECT * FROM pet WHERE petType = 'Cat';

SELECT * FROM pet WHERE age > 4 ORDER BY age DESC;

-- Average age by type
SELECT petType, AVG(age) as avg_age, COUNT(*) as count
FROM pet
GROUP BY petType;

