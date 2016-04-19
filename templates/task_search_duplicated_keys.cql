// This task searches the duplicated keys between 2 files.

merge (task:Task:Template { name: "Duplicate keys", description: "Search records in two files with common keys", uuid: "0f4af60b-ba74-435e-9f78-7ab8dcaa5a31" } )
merge (file1Param:Parameter:Template {name: "firstInputFilename", description: "Path of the first input file", uuid: "444269e0-ad38-48b2-b781-29d0c405902e"}) 
merge (file2Param:Parameter:Template {name: "secondInputFilename", description: "Path of the second input file", uuid: "6751ee44-b9da-446b-b15b-8bc98c4f60c4"}) 
merge (keys1Param:Parameter:Template {name: "firstKeys", description: "Keys of the first input file", uuid: "ce227203-8106-49d0-a51f-42747459fdef"})
merge (keys2Param:Parameter:Template {name: "secondKeys", description: "Keys of the second input file", uuid: "dcb7f3db-5a2f-47ce-98a3-575585e6e972"})
merge (duplicatesFirstFileParam:Parameter:Template {name: "duplicatesFirstFile", description: "Filepath with the duplicated records from the first file", uuid: "25d97989-46c3-42fc-ae61-6567ac63ced1"})
merge (duplicatesSecondFileParam:Parameter:Template {name: "duplicatesSecondFile", description: "Filepath with the duplicated records from the second file", uuid: "150cf33c-b4f6-4c12-8d4f-7f6337d9744a"})
merge (uniquesFirstFileParam:Parameter:Template {name: "uniquesFirstFile", description: "Filepath with the unique records from the first file", uuid: "e5122dd2-c2ce-4e7f-9bbe-b739787c7fa1"})
merge (uniquesSecondFileParam:Parameter:Template {name: "uniquesSecondFile", description: "Filepath with the unique records from the second file", uuid: "b1758617-3c8c-4ba4-be1b-0b7a919c40a6"})

merge (task)-[:HAS_PARAMETER]->(file1Param)
merge (task)-[:HAS_PARAMETER]->(file2Param)
merge (task)-[:HAS_PARAMETER]->(keys1Param)
merge (task)-[:HAS_PARAMETER]->(keys2Param)
merge (task)-[:HAS_PARAMETER]->(duplicatesFirstFileParam)
merge (task)-[:HAS_PARAMETER]->(uniquesFirstFileParam)
merge (task)-[:HAS_PARAMETER]->(duplicatesSecondFileParam)
merge (task)-[:HAS_PARAMETER]->(uniquesSecondFileParam)

merge (sort1:Step:Template {name: "Sorting the first file", description: "Sort the first file", uuid: "1cc741de-04f6-42c2-a9ea-49effd7eb85f"})
merge (inputFileSort1:Parameter:Template {name: "inputFile", uuid: "18c1ebce-1eb0-400e-ab5f-01d0da63de20"})
merge (outputFileSort1:Parameter:Template {name: "sortedFile", uuid: "ad4001b1-43c4-42ff-a0e1-f6a2233b017b"})
merge (keysSort1:Parameter:Template {name: "sortingKeys", uuid: "6cbc241d-4398-48d5-a8be-a354d35778cd"})

merge (sort1)-[:HAS_PARAMETER]->(inputFileSort1)
merge (sort1)-[:HAS_PARAMETER]->(outputFileSort1)
merge (sort1)-[:HAS_PARAMETER]->(keysSort1)

merge (sort2:Step:Template {name: "Sorting the second file", description: "Sort the second file", uuid: "bf134fb3-c603-4dda-ac8a-b876a057f265"})
merge (inputFileSort2:Parameter:Template {name: "inputFile", uuid: "0c005410-98c0-4ba0-a57b-e3da91be54de"})
merge (outputFileSort2:Parameter:Template {name: "sortedFile", uuid: "61d73b71-c0e6-447f-99ed-32fd261a462d"})
merge (keysSort2:Parameter:Template {name: "sortingKeys", uuid: "876e0c56-3030-4a0d-b936-941c28579124"})

merge (sort2)-[:HAS_PARAMETER]->(inputFileSort2)
merge (sort2)-[:HAS_PARAMETER]->(outputFileSort2)
merge (sort2)-[:HAS_PARAMETER]->(keysSort2)

merge (search:Step:Template {name: "Searching duplicates", description: "Search the duplicates between files", uuid: "49d550ae-03b9-45f2-b15b-4b7fb2ba9738"})
merge (firstInputSearch:Parameter:Template {name: "firstInputFile" , uuid: "47c63982-472d-4a87-b899-ef1049b223a2"})
merge (secondInputSearch:Parameter:Template {name: "secondInputFile", uuid: "1e5c87aa-9235-4d83-9431-394a94549bef"})
merge (firstKeysSearch:Parameter:Template {name: "firstKeys", uuid: "b4a433ca-d12f-43d7-a910-8002af177db4"})
merge (secondKeysSearch:Parameter:Template {name: "secondKeys", uuid: "45f15502-e2b9-4d43-9426-cff778fe0762"})
merge (duplicatesFirstFileSearch:Parameter:Template {name: "firstDuplicatesFile", uuid: "7adf5125-e1ae-4e31-ae5a-c39ac4a18b5a"})
merge (uniquesFirstFileSearch:Parameter:Template {name: "firstUniquesFile", uuid: "97fc6265-b43f-4a09-887d-87056ff1d95e"})
merge (duplicatesSecondFileSearch:Parameter:Template {name: "secondDuplicatesFile", uuid: "27921c13-9a35-42b2-b7ad-413ab1c18d39"})
merge (uniquesSecondFileSearch:Parameter:Template {name: "secondUniquesFile", uuid: "b7fbbd6c-fe50-41a7-b92a-08f161f79dd7"})

merge (search)-[:HAS_PARAMETER]->(firstInputSearch)
merge (search)-[:HAS_PARAMETER]->(secondInputSearch)
merge (search)-[:HAS_PARAMETER]->(firstKeysSearch)
merge (search)-[:HAS_PARAMETER]->(secondKeysSearch)
merge (search)-[:HAS_PARAMETER]->(duplicatesFirstFileSearch)
merge (search)-[:HAS_PARAMETER]->(duplicatesSecondFileSearch)
merge (search)-[:HAS_PARAMETER]->(uniquesFirstFileSearch)
merge (search)-[:HAS_PARAMETER]->(uniquesSecondFileSearch)

merge (sort1)-[:HAS_STEP]->(search)
merge (sort2)-[:HAS_STEP]->(search)

merge (task)-[:HAS_STEP]->(sort1)
merge (task)-[:HAS_STEP]->(sort2)


merge (file1Param)-[:INITIALIZES]->(inputFileSort1)
merge (file2Param)-[:INITIALIZES]->(inputFileSort2)
merge (keys1Param)-[:INITIALIZES]->(keysSort1)
merge (keys2Param)-[:INITIALIZES]->(keysSort2)
merge (keys1Param)-[:INITIALIZES]->(firstKeysSearch)
merge (keys2Param)-[:INITIALIZES]->(secondKeysSearch)
merge (duplicatesFirstFileParam)-[:INITIALIZES]->(duplicatesFirstFileSearch)
merge (uniquesFirstFileParam)-[:INITIALIZES]->(uniquesFirstFileSearch)
merge (duplicatesSecondFileParam)-[:INITIALIZES]->(duplicatesSecondFileSearch)
merge (uniquesSecondFileParam)-[:INITIALIZES]->(uniquesSecondFileSearch)

merge (outputFileSort1)-[:INITIALIZES]->(firstInputSearch)
merge (outputFileSort2)-[:INITIALIZES]->(secondInputSearch)


;



