ALTER TABLE developer
ADD salary int;

ALTER TABLE project
ADD cost int;

ALTER TABLE developer
ADD onleave boolean;

-- The most expensive project for developer's salaries
SELECT p.name, SUM(d.salary) AS salaries_sum
FROM project AS p
LEFT JOIN developer_project AS ds ON p.id = ds.project_id
LEFT JOIN developer AS d ON ds.developer_id = d.id
GROUP BY p.name
ORDER BY salaries_sum DESC
LIMIT 1;

-- The total salary for Java developers
SELECT s.industry, SUM(d.salary) AS salaries_sum
FROM skill AS s
LEFT JOIN developer_skill AS ds ON s.id = ds.skill_id
LEFT JOIN developer AS d ON ds.developer_id = d.id
WHERE s.industry = 'Java'
GROUP BY s.industry;

-- The cheapest project for the price
SELECT p.* FROM project AS p
WHERE cost = (SELECT MIN(cost) FROM project);

-- The average salary of the developer of the cheapest project
SELECT p.name, p.cost, ROUND(AVG(d.salary),2) AS average_salary FROM project AS p
LEFT JOIN developer_project AS ds ON p.id = ds.project_id
LEFT JOIN developer AS d ON ds.developer_id = d.id
GROUP BY p.name, p.cost
ORDER BY p.cost ASC
LIMIT 1;