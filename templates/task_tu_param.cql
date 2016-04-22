create (t:Task:Template { name: "TU PARAM", uuid: "3364e06b-fc61-4c6f-8e1a-5b39bba31409" })
create (s1:Step:Template { name: "STEP 1", uuid: "57272ac0-d0e8-45ab-b810-f9b5f554a0dd" })
create (s2:Step:Template { name: "STEP 2", uuid: "073353a4-8eca-4d32-8722-d6ddaa310bb0" })
create (s3:Step:Template { name: "STEP 3", uuid: "44d06b33-94ac-48bd-b733-aaa7a2e14300" })

merge (t)-[:HAS_STEP]->(s1)
merge (t)-[:HAS_STEP]->(s2)
merge (s2)-[:HAS_STEP]->(s3)

create (p1:Parameter:Template { name: "PARAM 1", uuid: "db95cd92-64ce-4adb-92eb-40dd8c7e7b7a" })
create (p2:Parameter:Template { name: "PARAM 2", uuid: "4e20992b-6dbb-4829-9625-9b391edfb03a" })
create (p3:Parameter:Template { name: "PARAM 3", uuid: "2e853afd-85e4-4d9d-acb1-f23e3cf58a7f" })

merge (t)-[:HAS_PARAMETER]->(p1)
merge (s1)-[:HAS_PARAMETER]->(p2)
merge (s3)-[:HAS_PARAMETER]->(p3)

merge (p1)-[:INITIALIZES]->(p3);



