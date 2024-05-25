set HOMEPATH=%CD%
cd %HOMEPATH% && rmdir /S /Q cntrlflow-ui\.next && rmdir /S /Q cntrlflow-ui\out
mvn clean package