

set studentId=race3

mvnw.cmd --batch-mode archetype:generate ^
  -DinteractiveMode=false ^
  -DarchetypeGroupId=edu.pdx.cs410J ^
  -DarchetypeArtifactId=student-archetype ^
  -DgroupId=edu.pdx.cs410J.%studentId% ^
  -DartifactId=student ^
  -Dpackage=edu.pdx.cs410J.%studentId% ^
  -Dversion=Winter2020
