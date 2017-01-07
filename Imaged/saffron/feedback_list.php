    <?php

        $con=mysqli_connect("localhost","root","","haney");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
		 //$indicator_cat = $_POST['indicator'];

           $i=mysqli_query($con, "select * from saffron")or die (mysql_error());

           $num_rows = mysqli_num_rows($i);
               $check = NULL;
          while($row = mysqli_fetch_assoc($i))
            {
  
                  $r[]=$row;
                  $check=$row['index'];
             }
			 
         if($check==NULL)
           {            
                      echo "Record is not available";
                      //print(json_encode($r));
                 
             }
            else
             {
                $r[$num_rows]="success";
                 print(json_encode($r));
				//echo json_decode($r);
              } 



 mysqli_close($con);
               
    ?> 
