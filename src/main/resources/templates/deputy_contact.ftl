<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Recuperación de Credenciales</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <style>
        body {
            font-family: "Times New Roman", Times, serif;
            font-size: 28px;
        }

	hr.new1 {
	  border: 1px solid #d6d6c2;
	}

	img {
	  border-radius: 50%;
	  display: block;
	
	}
    </style>
</head>
<body >

    <table align="center"cellpadding="0" cellspacing="0" width="0" style="border-style: solid; border-color:#d6d6c2">
        <tr>
            <td bgcolor="#fff" style="padding: 0px 30px 0px 30px;">
                <p align="center"><b>Estimado ciudadano ${name},<br> Bienvenido a Di, Diputado Digital, la plataforma que te acerca con tus representantes populares para dar respuesta a tus peticiones.<br></b></p>
				<hr class="new1">
				<p align="center">Te hemos enviado este correo para atender tu petición.</p>
				<br>
				<p align="center"><b> Estas son tus credenciales: </b></p>
				<br>
                <p align="center">Usuario: <b> ${user}</b> </p>
                <p align="center"> Contraseña: <b>${secret}</b> </p>
                <p align="center"><b>¡Juntos haremos mejorar al país!</b></p>
				<p align="center">Atentamente, <br> Diputado Digital</p>
            </td>
        </tr>

        <tr>
            <td bgcolor="#fff" style="padding: 0px 30px 30px 30px;">
                <p align="center">Visitanos en: <a href="https://test-ddch.firebaseapp.com">www.midiputadodigital.com</a></p>
                <p align="center">Yosemite 37, Col. Napoles, CDMX.</p>
            </td>
        </tr>
    </table>

</body>