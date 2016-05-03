  // This task searches the duplicated keys between 2 files.

  merge (task:Task:Template { name: "Duplicate keys", description: "Search records in two files with common keys", uuid: "b653bb2e-bc80-490b-81f0-5c904e331174" } )
  merge (file1Param:Parameter:Template {name: "firstInputFilename", description: "Path of the first input file", uuid: "502aaee2-357f-440c-b20e-b032127ea5c1"}) 
  merge (file2Param:Parameter:Template {name: "secondInputFilename", description: "Path of the second input file", uuid: "54870e07-2c7a-406f-8202-6567d85ae830"}) 
  merge (keys1Param:Parameter:Template {name: "firstKeys", description: "Keys of the first input file", uuid: "9d3d293c-25f9-46ba-9261-8567e5597417"})
  merge (keys2Param:Parameter:Template {name: "secondKeys", description: "Keys of the second input file", uuid: "2503872a-a4c4-4b7e-b359-2f74c986f70d"})
  merge (duplicatesFirstFileParam:Parameter:Template {name: "duplicatesFirstFile", description: "Filepath with the duplicated records from the first file", uuid: "c139d7be-d27c-4058-8e20-fd2f164087a6"})
  merge (duplicatesSecondFileParam:Parameter:Template {name: "duplicatesSecondFile", description: "Filepath with the duplicated records from the second file", uuid: "7d412da0-c321-46bd-b21c-7b49f83336bb"})
  merge (uniquesFirstFileParam:Parameter:Template {name: "uniquesFirstFile", description: "Filepath with the unique records from the first file", uuid: "2019d29c-bd16-4d09-8c77-d94654360518"})
  merge (uniquesSecondFileParam:Parameter:Template {name: "uniquesSecondFile", description: "Filepath with the unique records from the second file", uuid: "f8c14d40-95bc-494f-97e1-f659acc9044c"})

  merge (task)-[:HAS_PARAMETER]->(file1Param)
  merge (task)-[:HAS_PARAMETER]->(file2Param)
  merge (task)-[:HAS_PARAMETER]->(keys1Param)
  merge (task)-[:HAS_PARAMETER]->(keys2Param)
  merge (task)-[:HAS_PARAMETER]->(duplicatesFirstFileParam)
  merge (task)-[:HAS_PARAMETER]->(uniquesFirstFileParam)
  merge (task)-[:HAS_PARAMETER]->(duplicatesSecondFileParam)
  merge (task)-[:HAS_PARAMETER]->(uniquesSecondFileParam)

  merge (sort1:Step:Template {name: "Sorting the first file", description: "Sort the first file", uuid: "7a15ca15-23cf-4ef2-ae84-f8d158ad5d0b"})
  merge (inputFileSort1:Parameter:Template {name: "inputFile", uuid: "6830742f-b339-408a-aea8-4da38b0016c5"})
  merge (outputFileSort1:Parameter:Template {name: "sortedFile", uuid: "d68e1bd5-97b3-48cb-9578-04231c3cfd78"})
  merge (keysSort1:Parameter:Template {name: "sortingKeys", uuid: "cc74ed64-29c6-4054-87db-751749e24af7"})

  merge (sort1)-[:HAS_PARAMETER]->(inputFileSort1)
  merge (sort1)-[:HAS_PARAMETER]->(outputFileSort1)
  merge (outputFileSort1)<-[:GENERATES]-(g:FilepathParameterGenerator:Template {uuid: "e44366e6-b077-4a77-8f88-bfb0d9a7f6c0"})
  merge (sort1)-[:HAS_PARAMETER]->(keysSort1)


  merge (sort2:Step:Template {name: "Sorting the second file", description: "Sort the second file", uuid: "a57a5cac-704d-477d-85df-09ef551ebfa2"})
  merge (inputFileSort2:Parameter:Template {name: "inputFile", uuid: "70c063df-7d7c-4480-84f6-8adf53261b8d"})
  merge (outputFileSort2:Parameter:Template {name: "sortedFile", uuid: "3a2a604f-ed49-4834-b7a4-255187cfc530"})
  merge (outputFileSort2)<-[:GENERATES]-(g:FilepathParameterGenerator:Template {uuid: "2fb7d8d4-5433-4c71-9b85-f908b932c4c2"})
  merge (keysSort2:Parameter:Template {name: "sortingKeys", uuid: "9edf3056-410a-41b1-868a-e57777af2818"})

  merge (sort2)-[:HAS_PARAMETER]->(inputFileSort2)
  merge (sort2)-[:HAS_PARAMETER]->(outputFileSort2)
  merge (sort2)-[:HAS_PARAMETER]->(keysSort2)

  merge (search:Step:Template {name: "Searching duplicates", description: "Search the duplicates between files", uuid: "734d6b60-4baa-4c1d-b0f7-a71f221f014e"})
  merge (firstInputSearch:Parameter:Template {name: "firstInputFile" , uuid: "3f158d04-a255-4c82-afa3-51bb7b11d318"})
  merge (secondInputSearch:Parameter:Template {name: "secondInputFile", uuid: "0d8702fd-72de-47e9-af17-23a65d112927"})
  merge (firstKeysSearch:Parameter:Template {name: "firstKeys", uuid: "c4c291aa-3633-4636-b337-5c28cbac7789"})
  merge (secondKeysSearch:Parameter:Template {name: "secondKeys", uuid: "4c539da7-733b-48ae-a134-56812a327b6d"})
  merge (duplicatesFirstFileSearch:Parameter:Template {name: "firstDuplicatesFile", uuid: "b000a9f5-903f-49a5-b5f8-f9859b8c0c12"})
  merge (uniquesFirstFileSearch:Parameter:Template {name: "firstUniquesFile", uuid: "453ead77-6aac-42d5-aba7-1fd1d1fa7f38"})
  merge (duplicatesSecondFileSearch:Parameter:Template {name: "secondDuplicatesFile", uuid: "a7892d76-2b6b-490e-986f-c17f8304ab02"})
  merge (uniquesSecondFileSearch:Parameter:Template {name: "secondUniquesFile", uuid: "d9e4c80a-b405-4e3d-9b04-243469b0bbd8"})

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

  // The output values
  merge (duplicatesFirstFileOutputSearch:Output:Template {uuid: "b521f081-c218-4597-8f66-dbffc6f2a420", name: "duplicatesFirstFile"})
  merge (uniquesFirstFileOutputSearch:Output:Template {uuid: "3cb770ce-ceba-4dae-96b3-054a28fb4502", name: "uniquesFirstFile"})
  merge (duplicatesSecondFileOutputSearch:Output:Template {uuid: "d58b9d74-6a82-4aa1-954b-1a2b948a6c8f", name: "duplicatesSecondFile"})
  merge (uniquesSecondFileOutputSearch:Output:Template {uuid: "fad7aed0-8201-42fc-a1ef-e0f7f37b8855", name: "uniquesSecondFile"})

  merge (duplicatesFirstFileOutputSearch)<-[:PRODUCES]-(search)
  merge (uniquesFirstFileOutputSearch)<-[:PRODUCES]-(search)
  merge (duplicatesSecondFileOutputSearch)<-[:PRODUCES]-(search)
  merge (uniquesSecondFileOutputSearch)<-[:PRODUCES]-(search)
  

  // exposes the output values
  merge (task)-[:EXPOSES]->(duplicatesFirstFileOutputSearch)
  merge (task)-[:EXPOSES]->(uniquesFirstFileOutputSearch)
  merge (task)-[:EXPOSES]->(duplicatesSecondFileOutputSearch)
  merge (task)-[:EXPOSES]->(uniquesSecondFileOutputSearch)
  
  ;



