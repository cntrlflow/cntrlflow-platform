set HOMEPATH=%CD%
cd %HOMEPATH% && rmdir /S /Q cntrlflow-ui\dist
mvn clean package