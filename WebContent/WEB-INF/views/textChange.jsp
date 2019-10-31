<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>画像から文字を起こすよ</title>
</head>
<body>

	<h1>画像から文字をおこします</h1>
	<p>文字を起こしたい画像をアップロードしてください。</p>
	<ul>
		<li><strong>注意事項</strong></li>
		<li>画像は１枚のみアップロードしてください。PDFも1枚のみ</li>
		<li>アップロード画像の容量は500MBまでです。</li>
	</ul>

	<form action="" method="post" enctype="multipart/form-data">
		<p>
			文字起こしをしたい言語を選択してください：<select name="lang">
				<option value="jpn" selected>日本語</option>
				<option value="eng">英語</option>
			</select>
		</p>
		<input type="file" name="file" /><br /> <input type="submit"
			value="アップロード" />
	</form>

    <section>
			<pre style="font-size:16px; color:red;"><c:out value="${str}" /></pre>
	</section>
</body>
</html>