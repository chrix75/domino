// Template for the "Number a file" task.
// This task takes an input file and adds a record id field at the end of each record.

create index on :Template(uuid);
create index on :Task(name);

create (task:Task:Template {uuid: "5b922e85-8695-4cae-9ac6-1f7346f3426a", name: "Number file records", description: "Add a record id to each record"})
create (step:Step:Template {uuid: "a72946c6-b18a-44c3-b822-be1661d27a92", name: "Adding record id field", description: "Add a record id to each record"})
create (param1_task:Parameter:Template {uuid: "0d754f48-0a85-484b-88df-28721985b841", name: "inputFile", description: "The path of the file to process"})
create (param2_task:Parameter:Template {uuid: "4e0fc293-d696-4a81-9f28-10af34ac9570", name: "outputFile", description: "The path of the result file"})
create (param3_task:Parameter:Template {uuid: "5914d7d8-577e-4f17-9e0d-5bcd5a55d2b2", name: "algorithm", description: "Algorithm name computes the record ID"})
create (param1_step:Parameter:Template {uuid: "1797da57-1b51-41b4-acb7-ff153e6fd55f", name: "inputFile", description: "The path of the file to process"})
create (param2_step:Parameter:Template {uuid: "278f75b0-1cf1-4ecd-9aef-41f64dc2c868", name: "outputFile", description: "The path of the result file"})
create (param3_step:Parameter:Template {uuid: "978c118f-7c3c-409f-9ef1-a904d6a8fa28", name: "algorithm", description: "Algorithm name computes the record ID"})
merge (task)-[:HAS_STEP]->(step)
merge (task)-[:HAS_PARAMETER]->(param1_task)
merge (task)-[:HAS_PARAMETER]->(param2_task)
merge (task)-[:HAS_PARAMETER]->(param3_task)
merge (step)-[:HAS_PARAMETER]->(param1_step)
merge (step)-[:HAS_PARAMETER]->(param2_step)
merge (step)-[:HAS_PARAMETER]->(param3_step)
merge (param1_task)-[:INITIALIZES]->(param1_step)
merge (param2_task)-[:INITIALIZES]->(param2_step)
merge (param3_task)-[:INITIALIZES]->(param3_step);




